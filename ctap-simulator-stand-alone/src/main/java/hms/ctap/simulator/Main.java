package hms.ctap.simulator;

import javax.management.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;

import hms.ctap.simulator.ui.AppListComboRenderer;
import hms.ctap.simulator.ui.SmsUiFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.management.ManagementFactory;
import java.net.URL;

public class Main extends JFrame implements MainMBean {
    static SmsUiFactory smsUiFactory;
    private String applicationURL;
    private JLabel applicationURLLabel;
    private JComboBox applicationDropDown;

    public Main() {
        this.applicationURLLabel = new JLabel("Hello world");
        JPanel contentPanel = new JPanel();
        smsUiFactory = new SmsUiFactory(contentPanel);
        String[] applicationList = {"SMS$$http://localhost:8080/sms/helloWorld"};
        applicationDropDown = new JComboBox(applicationList);

        applicationDropDown.setRenderer(new AppListComboRenderer());

        applicationDropDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] splitedValues = applicationDropDown.getSelectedItem().toString().split("\\$\\$");
                smsUiFactory.setApplicationPath(splitedValues[1]);
                System.out.println("Selected app url - " +splitedValues[1]);
            }
        });

        applicationDropDown.setPreferredSize(new Dimension(590, 45));

        JPanel bottomLayer = new JPanel();
//        JLabel appListLable = new JLabel("APPS");
//        bottomLayer.add(appListLable,BorderLayout.WEST);
        bottomLayer.add(applicationDropDown);

        Container pane = getContentPane();
        pane.add(smsUiFactory.createInitialUI());
        pane.add(bottomLayer,BorderLayout.SOUTH);

        ImageIcon icon = new ImageIcon(getImage("hms_logo.png"));
        setIconImage(icon.getImage());
        setTitle("hSenid Mobile - CTAP Simulator");
        setSize(600, 660);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private URL getImage(String imageName) {
        return getClass().getResource("/ctap-simulator/images/" + imageName);
    }

    public static void main(String[] args) throws Exception {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main mainApp = new Main();
                ObjectName name;
                MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

                try {
                    name = new ObjectName("hms.ctap.simulator:type=Main");
                    mbs.registerMBean(mainApp, name);
                } catch (MalformedObjectNameException e) {
                    e.printStackTrace();
                } catch (NotCompliantMBeanException e) {
                    e.printStackTrace();
                } catch (InstanceAlreadyExistsException e) {
                    e.printStackTrace();
                } catch (MBeanRegistrationException e) {
                    e.printStackTrace();
                }
                mainApp.setVisible(true);
            }
        });

        SimulatorServer simulatorServer = new SimulatorServer();
        simulatorServer.start();
    }

    @Override
    public void addApplication(String type, String urlString) {
        this.applicationDropDown.addItem(type+"$$"+urlString);
    }


}