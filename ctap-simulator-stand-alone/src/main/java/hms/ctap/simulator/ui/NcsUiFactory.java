package hms.ctap.simulator.ui;

import javax.swing.*;
import java.net.URL;

public class NcsUiFactory {

    SpringLayout bgLayout;
    JTextArea displayTextArea;
    JPanel toolWindowContent;
    JPanel elementContainer;
    JLabel phoneImage;
    String applicationPath;

    public NcsUiFactory(JPanel toolWindowContent) {
        this.toolWindowContent = toolWindowContent;
        this.bgLayout = new SpringLayout();
        this.elementContainer = new JPanel(bgLayout);
        this.phoneImage = new JLabel(new ImageIcon(getImage("phone.png")));
    }

    protected URL getImage(String imageName) {
        return getClass().getResource("/ctap-simulator/images/" + imageName);
    }

    public void setApplicationPath(String applicationPath) {
        this.applicationPath = applicationPath;
    }


}