package hms.ctap.simulator;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import hms.ctap.simulator.ui.AppListComboRenderer;
import hms.ctap.simulator.ui.SmsUiFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

public class Main extends JFrame {
    static SmsUiFactory smsUiFactory;
    private String applicationURL;
    private JLabel applicationURLLabel;

    public Main() {
        this.applicationURLLabel = new JLabel("Hello world");
        JPanel contentPanel = new JPanel();
        smsUiFactory = new SmsUiFactory(contentPanel);

        String[] applicationList = {"sms$$http://localhost:8081/sms-","ussd$$http://localhost:8081/ussd-"};
        final JComboBox applicationDropDown = new JComboBox(applicationList);

        applicationDropDown.setRenderer(new AppListComboRenderer());

        applicationDropDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] splitedValues = applicationDropDown.getSelectedItem().toString().split("\\$\\$");
                smsUiFactory.setApplicationPath(splitedValues[1]);
                System.out.println("Selected app url - " +splitedValues[1]);
            }
        });

/*        applicationDropDown.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                String newUrl = ((JTextComponent) ((JComboBox) ((Component) event
                        .getSource()).getParent()).getEditor()
                        .getEditorComponent()).getText();
                System.out.println(newUrl);
                smsUiFactory.setApplicationPath(newUrl);
            }
        });
*/

//        applicationDropDown.setEditable(true);

        applicationDropDown.setPreferredSize(new Dimension(590, 45));

        JPanel bottomLayer = new JPanel();
        JLabel appListLable = new JLabel("APPS");

//        bottomLayer.add(appListLable,BorderLayout.WEST);
        bottomLayer.add(applicationDropDown);


        Container pane = getContentPane();
        pane.add(smsUiFactory.createInitialUI());
        pane.add(bottomLayer,BorderLayout.SOUTH);


        ImageIcon icon = new ImageIcon (Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ctap-simulator/images/hms_logo.png")));
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

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main mainApp = new Main();
                mainApp.setVisible(true);

            }
        });

        SimulatorServer simulatorServer = new SimulatorServer();
        simulatorServer.start();

    }
}