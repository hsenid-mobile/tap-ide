package hms.ctap.simulator;

import javax.swing.*;

import hms.ctap.simulator.ui.SmsUiFactory;
import hms.ctap.simulator.NblServerSimulator;

import java.io.*;

import java.awt.*;

public class Main extends JFrame {
    static SmsUiFactory smsUiFactory;

    public Main() {
        JPanel jPanel = new JPanel();
        smsUiFactory = new SmsUiFactory(jPanel);
        smsUiFactory.createInitialUI();

        Container pane = getContentPane();

        pane.add(smsUiFactory.createInitialUI());


        setTitle("Simple example");
        setSize(600, 650);
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