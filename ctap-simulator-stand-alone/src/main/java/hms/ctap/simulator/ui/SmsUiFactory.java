package hms.ctap.simulator.ui;

import com.google.gson.Gson;
import hms.kite.samples.api.sms.messages.MoSmsReq;
import hms.kite.samples.api.sms.messages.MtSmsResp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;

public class SmsUiFactory extends NcsUiFactory {


    public SmsUiFactory(JPanel toolWindowContent) {
        super(toolWindowContent);
    }

    public JPanel createInitialUI() {

        elementContainer.removeAll();

        JLabel smsBg = new JLabel(new ImageIcon(getImage("sms_bg.png")));

        JPanel msgBox = new JPanel();
        msgBox.setBackground(Color.BLACK);

        final JTextArea msgText = new JTextArea(6, 12);
        Font font = new Font("Verdana", Font.BOLD, 12);
        msgText.setFont(font);
        msgText.setBackground(Color.BLACK);
        msgText.setForeground(Color.WHITE);
        msgBox.setPreferredSize(new Dimension(180, 100));
        msgText.setPreferredSize(new Dimension(175, 90));
        msgText.setLineWrap(true);

        JPanel addressBox = new JPanel();
        addressBox.setBackground(Color.BLACK);

        JTextArea addressText = new JTextArea(1, 12);
        addressText.setFont(font);
        addressText.setBackground(Color.BLACK);
        addressText.setForeground(Color.WHITE);
        addressBox.setPreferredSize(new Dimension(180, 20));
        addressText.setPreferredSize(new Dimension(180, 20));
        addressText.setLineWrap(true);

        JLabel btnSmsSend = new JLabel(new ImageIcon(getImage("sms_send.png")));
        btnSmsSend.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String appPath = "http://localhost" + ":" + "8080" + "/" + "smsSample" + "/mo-receiver";
                try {
                    Object response = null;

                    String requestFormat = "{\n" +
                            "\"message\": \""+ msgText.getText() +"\",\n" +
                            "\"requestId\":\"51307311302350037\",\n" +
                            "\"applicationId\":\"APP_000006\",\n" +
                            "\"sourceAddress\":\"tel:947712345678\",\n" +
                            "\"version\":\"1.0\",\n" +
                            "\"encoding\":\"0\"\n" +
                            "}";

                    Gson gson = new Gson();
                    URL url = new URL(appPath);
                    URLConnection urlConnection = url.openConnection();
                    urlConnection.setDoOutput(true);
                    urlConnection.setRequestProperty("Content-Type", "application/json");
                    urlConnection.setRequestProperty("Accept", "application/json");
                    OutputStreamWriter connection1 = new OutputStreamWriter(urlConnection.getOutputStream());
                    connection1.write(requestFormat);
                    connection1.flush();
                    BufferedReader wr1 = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder rd1 = new StringBuilder();

                    String content1;
                    while((content1 = wr1.readLine()) != null) {
                        rd1.append(content1);
                        rd1.append("\n");
                    }
                    connection1.close();
                    wr1.close();
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        addressBox.add(addressText);
        msgBox.add(msgText);

        elementContainer.add(btnSmsSend);
        elementContainer.add(addressBox);
        elementContainer.add(msgBox);
        elementContainer.add(smsBg);
        elementContainer.add(phoneImage);


        bgLayout.putConstraint(SpringLayout.WEST, smsBg, 173, SpringLayout.WEST, phoneImage);
        bgLayout.putConstraint(SpringLayout.NORTH, smsBg, 125, SpringLayout.NORTH, phoneImage);
        bgLayout.putConstraint(SpringLayout.WEST, msgBox, 188, SpringLayout.WEST, phoneImage);
        bgLayout.putConstraint(SpringLayout.NORTH, msgBox, 210, SpringLayout.NORTH, phoneImage);
        bgLayout.putConstraint(SpringLayout.WEST, addressBox, 188, SpringLayout.WEST, phoneImage);
        bgLayout.putConstraint(SpringLayout.NORTH, addressBox, 175, SpringLayout.NORTH, phoneImage);
        bgLayout.putConstraint(SpringLayout.WEST, btnSmsSend, 378, SpringLayout.WEST, phoneImage);
        bgLayout.putConstraint(SpringLayout.NORTH, btnSmsSend, 243, SpringLayout.NORTH, phoneImage);


        elementContainer.revalidate();
        elementContainer.repaint();

        return elementContainer;

    }

    public JPanel createMsgReceivedUI(String message) {

        elementContainer.removeAll();

        JLabel responseBg = new JLabel(new ImageIcon(getImage("response.png")));
        JPanel responseBox = new JPanel(new BorderLayout());
        Font font = new Font("Verdana", Font.BOLD, 12);

        JTextArea responseText = new JTextArea(10, 20);
        responseText.append(message);
        responseText.setFont(font);
        responseText.setLineWrap(true);
        responseText.setBackground(new Color(29, 46, 60));

        JButton btnOK = new JButton("OK");
        btnOK.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                createInitialUI();
            }
        });
        responseBox.add(btnOK, BorderLayout.SOUTH);
        responseBox.setBackground(new Color(29, 46, 60));
        responseBox.add(responseText);
        responseText.setBackground(new Color(29, 46, 60));
        responseText.setForeground(Color.WHITE);

        elementContainer.add(responseBox);
        elementContainer.add(responseBg);
        elementContainer.add(phoneImage);


        bgLayout.putConstraint(SpringLayout.WEST, responseBg, 173, SpringLayout.WEST, phoneImage);
        bgLayout.putConstraint(SpringLayout.NORTH, responseBg, 125, SpringLayout.NORTH, phoneImage);
        bgLayout.putConstraint(SpringLayout.WEST, responseBox, 182, SpringLayout.WEST, phoneImage);
        bgLayout.putConstraint(SpringLayout.NORTH, responseBox, 212, SpringLayout.NORTH, phoneImage);


        elementContainer.revalidate();
        elementContainer.repaint();
        return elementContainer;


    }

}