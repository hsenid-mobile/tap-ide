package hms.ctap.simulator;

import javax.swing.*;
import hms.ctap.simulator.ui.SmsUiFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    static SmsUiFactory smsUiFactory;
    private String applicationURL;
    private JLabel applicationURLLabel;

    public Main() {
        this.applicationURLLabel = new JLabel("Hello world");
        JPanel contentPanel = new JPanel();
        smsUiFactory = new SmsUiFactory(contentPanel);

        String[] applicationList = {"SMS: http://localhost:8080/helloWord","USSD: http://localhost:8081/helloWord-"};
        final JComboBox applicationDropDown = new JComboBox(applicationList);
        applicationDropDown.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                applicationURLLabel.setText(applicationDropDown.getSelectedItem().toString());
            }
        });
        applicationDropDown.setPreferredSize(new Dimension(500,20));

        SpringLayout bottomLayerLayout = new SpringLayout();
        JPanel bottomLayer = new JPanel();
        JLabel appListLable = new JLabel("Applications");

        bottomLayer.add(appListLable,BorderLayout.WEST);
        bottomLayer.add(applicationDropDown);
//        bottomLayer.add(applicationURLLabel,BorderLayout.WEST);

//        bottomLayerLayout.putConstraint(SpringLayout.WEST, applicationDropDown, 200, SpringLayout.WEST, appListLable);


        Container pane = getContentPane();
        pane.add(smsUiFactory.createInitialUI());
        pane.add(bottomLayer,BorderLayout.SOUTH);


        setTitle("hSenid Mobile - CTAP Simulator");
        setSize(600, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


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