package hms.ctap.simulator;

import javax.management.*;
import javax.swing.*;
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
    private JComboBox applicationListDropDown;
    private boolean isApplicationDropDownEmpty=true;
    private Container contentPane;
    private JPanel bottomLayer = new JPanel();

    public Main() {
        final JPanel contentPanel = new JPanel();
        smsUiFactory = new SmsUiFactory(contentPanel);
        ussdUiFactory = new UssdUiFactory(contentPanel);
        applicationListDropDown = createApplicationListDropDown();
        bottomLayer.add(applicationListDropDown);
        contentPane = getContentPane();
        contentPane.add(smsUiFactory.createInitialUI());
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

    private JComboBox createApplicationListDropDown() {
        String[] applicationList = {"NONE$$- No running application detected -"};
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(applicationList);
        final JComboBox appDropDown = new JComboBox(comboBoxModel);
        appDropDown.setRenderer(new AppListComboRenderer());
        appDropDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] splitedValues = appDropDown.getSelectedItem().toString().split("\\$\\$");
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
        appDropDown.setPreferredSize(new Dimension(590, 45));
        return appDropDown;
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

    private boolean isAppItemAlreadyExists(String applicationItem){
        return ((DefaultComboBoxModel) this.applicationListDropDown.getModel()).getIndexOf(applicationItem)!=-1;
    }

    @Override
    public void addApplication(String type, String urlString) {
        String applicationItem = type + "$$" + urlString;
        if (this.isApplicationDropDownEmpty){
            if (! isAppItemAlreadyExists(applicationItem)){
                this.applicationListDropDown.insertItemAt(applicationItem, 0);
                this.applicationListDropDown.removeItemAt(1);
                this.isApplicationDropDownEmpty=false;
            }
        } else {
            if (! isAppItemAlreadyExists(applicationItem)){
                this.applicationListDropDown.insertItemAt(applicationItem, 0);
            }
        }
        this.applicationListDropDown.setSelectedIndex(0);
    }
}