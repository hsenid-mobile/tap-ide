package hms.ctap.simulator;

import javax.management.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;

import hms.ctap.simulator.ui.AppListComboRenderer;
import hms.ctap.simulator.ui.SmsUiFactory;
import hms.ctap.simulator.ui.UssdUiFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.management.ManagementFactory;
import java.net.URL;

public class Main extends JFrame implements MainMBean {
    static SmsUiFactory smsUiFactory;
    static UssdUiFactory ussdUiFactory;
    private String applicationURL;
    private JLabel applicationURLLabel;
    private JComboBox applicationDropDown;
    private Container contentPane;
    private JPanel bottomLayer = new JPanel();

    public Main() {
        this.applicationURLLabel = new JLabel("Hello world");
        final JPanel contentPanel = new JPanel();
        smsUiFactory = new SmsUiFactory(contentPanel);
        ussdUiFactory = new UssdUiFactory(contentPanel);

        String[] applicationList = {"NONE$$- No running application detected -"};
        applicationDropDown = new JComboBox(applicationList);

        applicationDropDown.setRenderer(new AppListComboRenderer());

        applicationDropDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] splitedValues = applicationDropDown.getSelectedItem().toString().split("\\$\\$");
                if (splitedValues[0].equals("SMS")) {
                    contentPane.removeAll();
                    smsUiFactory.setApplicationPath(splitedValues[1]);
                    contentPane.add(smsUiFactory.createInitialUI());
                    contentPane.add(bottomLayer,BorderLayout.SOUTH);
                    contentPane.revalidate();
                    contentPane.repaint();
                } else if(splitedValues[0].equals("USSD")) {
                    contentPane.removeAll();
                    ussdUiFactory.setApplicationPath(splitedValues[1]);
                    contentPane.add(ussdUiFactory.createInitialUI());
                    contentPane.add(bottomLayer,BorderLayout.SOUTH);
                    contentPane.revalidate();
                    contentPane.repaint();
                }

                System.out.println("Selected app url - " +splitedValues[1]);
            }
        });

        applicationDropDown.setPreferredSize(new Dimension(590, 45));


//        JLabel appListLable = new JLabel("APPS");
//        bottomLayer.add(appListLable,BorderLayout.WEST);
        bottomLayer.add(applicationDropDown);

        contentPane = getContentPane();
//        contentPane.add(smsUiFactory.createInitialUI());
        contentPane.add(ussdUiFactory.createInitialUI());
        contentPane.add(bottomLayer,BorderLayout.SOUTH);

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