package hms.ctap.idea.plugin.ui;

import javax.swing.*;
import javax.swing.text.Document;
import java.net.URL;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.String;

import hms.ctap.idea.plugin.ui.NcsUiFactory;

public class UssdUiFactory extends NcsUiFactory {


    public UssdUiFactory(){

    }

    public JPanel createInitialUI() {
        SpringLayout  bgLayout = new SpringLayout();
        JPanel ussdInitialUI = new JPanel(bgLayout);

        displayTextArea = new JTextArea("", 1, 10);
        Font font = new Font("Verdana", Font.BOLD, 25);
        displayTextArea.setFont(font);
        displayTextArea.setLineWrap(true);
        displayTextArea.setBackground(Color.black);
        displayTextArea.setForeground(Color.WHITE);
        displayTextArea.removeAll();

        JLabel topIcons = new JLabel(new ImageIcon(getImage("top.png")));
        ussdInitialUI.add(topIcons);


        //Key pad elements
        GridBagLayout keyPadLayout = new GridBagLayout();
        JPanel keyPad = new JPanel(keyPadLayout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(1,1,1,1);
        keyPad.setPreferredSize(new Dimension(265, 243));
        keyPad.setBackground(Color.black);
        JLabel btn01 = new JLabel(new ImageIcon(getImage("01.png")));
        JLabel btn02 = new JLabel(new ImageIcon(getImage("02.png")));
        JLabel btn03 = new JLabel(new ImageIcon(getImage("03.png")));
        JLabel btn04 = new JLabel(new ImageIcon(getImage("04.png")));
        JLabel btn05 = new JLabel(new ImageIcon(getImage("05.png")));
        JLabel btn06 = new JLabel(new ImageIcon(getImage("06.png")));
        JLabel btn07 = new JLabel(new ImageIcon(getImage("07.png")));
        JLabel btn08 = new JLabel(new ImageIcon(getImage("08.png")));
        JLabel btn09 = new JLabel(new ImageIcon(getImage("09.png")));
        JLabel btn10 = new JLabel(new ImageIcon(getImage("10.png")));
        JLabel btn11 = new JLabel(new ImageIcon(getImage("11.png")));
        JLabel btn12 = new JLabel(new ImageIcon(getImage("12.png")));
        JLabel btn13 = new JLabel(new ImageIcon(getImage("13.png")));
        JLabel btn14 = new JLabel(new ImageIcon(getImage("14.png")));
        JLabel btn15 = new JLabel(new ImageIcon(getImage("15.png")));


        btn01.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                displayTextArea.append("1");
            }
        });

        btn02.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                displayTextArea.append("2");
            }
        });

        btn03.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                displayTextArea.append("3");
            }
        });

        btn04.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                displayTextArea.append("4");
            }
        });

        btn05.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                displayTextArea.append("5");
            }
        });

        btn06.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                displayTextArea.append("6");
            }
        });

        btn07.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                displayTextArea.append("7");
            }
        });

        btn08.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                displayTextArea.append("8");
            }
        });

        btn09.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                displayTextArea.append("9");
            }
        });

        btn10.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                displayTextArea.append("*");
            }
        });

        btn11.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                displayTextArea.append("0");
            }
        });

        btn12.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                displayTextArea.append("#");
            }
        });

        btn15.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Document doc = displayTextArea.getDocument();
                try{
                    doc.remove(doc.getLength() - 1, 1);
                } catch (Exception ex){

                }

            }
        });


        keyPad.add(btn01);
        keyPad.add(btn02);
        keyPad.add(btn03);

        ussdInitialUI.add(displayTextArea);
        GridBagConstraints gbcBtn04 = new GridBagConstraints();
        gbcBtn04.gridx=0;
        gbcBtn04.gridy=1;
        keyPadLayout.setConstraints(btn04, gbcBtn04);
        keyPad.add(btn04);

        GridBagConstraints gbcBtn05 = new GridBagConstraints();
        gbcBtn05.gridx=1;
        gbcBtn05.gridy=1;
        keyPadLayout.setConstraints(btn05,gbcBtn05);
        keyPad.add(btn05);

        GridBagConstraints gbcBtn06 = new GridBagConstraints();
        gbcBtn06.gridx=2;
        gbcBtn06.gridy=1;
        keyPadLayout.setConstraints(btn06,gbcBtn06);
        keyPad.add(btn06);
//--------------------------------------
        GridBagConstraints gbcBtn07 = new GridBagConstraints();
        gbcBtn07.gridx=0;
        gbcBtn07.gridy=2;
        keyPadLayout.setConstraints(btn07, gbcBtn07);
        keyPad.add(btn07);

        GridBagConstraints gbcBtn08 = new GridBagConstraints();
        gbcBtn08.gridx=1;
        gbcBtn08.gridy=2;
        keyPadLayout.setConstraints(btn08,gbcBtn08);
        keyPad.add(btn08);

        GridBagConstraints gbcBtn09 = new GridBagConstraints();
        gbcBtn09.gridx=2;
        gbcBtn09.gridy=2;
        keyPadLayout.setConstraints(btn09,gbcBtn09);
        keyPad.add(btn09);

        //--------------------------------------
        GridBagConstraints gbcBtn10 = new GridBagConstraints();
        gbcBtn10.gridx=0;
        gbcBtn10.gridy=3;
        keyPadLayout.setConstraints(btn10, gbcBtn10);
        keyPad.add(btn10);

        GridBagConstraints gbcBtn11 = new GridBagConstraints();
        gbcBtn11.gridx=1;
        gbcBtn11.gridy=3;
        keyPadLayout.setConstraints(btn11,gbcBtn11);
        keyPad.add(btn11);

        GridBagConstraints gbcBtn12 = new GridBagConstraints();
        gbcBtn12.gridx=2;
        gbcBtn12.gridy=3;
        keyPadLayout.setConstraints(btn12,gbcBtn12);
        keyPad.add(btn12);

//--------------------------------------

        GridBagConstraints gbcBtn13 = new GridBagConstraints();
        gbcBtn13.gridx=0;
        gbcBtn13.gridy=4;
        keyPadLayout.setConstraints(btn13, gbcBtn13);
        keyPad.add(btn13);

        GridBagConstraints gbcBtn14 = new GridBagConstraints();
        gbcBtn14.gridx=1;
        gbcBtn14.gridy=4;
        keyPadLayout.setConstraints(btn14,gbcBtn14);
        keyPad.add(btn14);

        GridBagConstraints gbcBtn15 = new GridBagConstraints();
        gbcBtn15.gridx=2;
        gbcBtn15.gridy=4;
        keyPadLayout.setConstraints(btn15,gbcBtn15);
        keyPad.add(btn15);
        ussdInitialUI.add(keyPad);


        //Top icons
        JLabel phoneImage = new JLabel(new ImageIcon(getImage("phone.png")));
        ussdInitialUI.add(phoneImage);

        bgLayout.putConstraint(SpringLayout.WEST,keyPad,171,SpringLayout.WEST,phoneImage);
        bgLayout.putConstraint(SpringLayout.NORTH,keyPad,240,SpringLayout.NORTH,phoneImage);
        bgLayout.putConstraint(SpringLayout.WEST,topIcons,173,SpringLayout.WEST,phoneImage);
        bgLayout.putConstraint(SpringLayout.NORTH,topIcons,125,SpringLayout.NORTH,phoneImage);
        bgLayout.putConstraint(SpringLayout.WEST,displayTextArea,173,SpringLayout.WEST,phoneImage);
        bgLayout.putConstraint(SpringLayout.NORTH,displayTextArea,180,SpringLayout.NORTH,phoneImage);

        return ussdInitialUI;

    }

}