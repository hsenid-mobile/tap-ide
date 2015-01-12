package hms.ctap.simulator;

import javax.swing.*;
import hms.ctap.simulator.ui.SmsUiFactory;
import hms.ctap.simulator.NblServerSimulator;
import java.io.*;

import java.awt.*;

public class Main extends JFrame{

    public Main(){
        JPanel jPanel = new JPanel();
        SmsUiFactory smsUiFactory = new SmsUiFactory(jPanel);
        smsUiFactory.createInitialUI();

        Container pane = getContentPane();

        pane.add(smsUiFactory.createInitialUI());


        setTitle("Simple example");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        try {
            final NblServerSimulator nblServerSimulator = new NblServerSimulator(smsUiFactory);
        } catch (IOException e) {
        e.printStackTrace();
    }

}

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main mainApp = new Main();
                mainApp.setVisible(true);
            }
        });

//        System.out.println("Hello world!!");
    }
}