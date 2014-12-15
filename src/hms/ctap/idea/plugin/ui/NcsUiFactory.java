package hms.ctap.idea.plugin.ui;

import javax.swing.*;
import java.net.URL;

public class NcsUiFactory {

    SpringLayout bgLayout;
    JTextArea displayTextArea;
    JPanel toolWindowContent;
    JPanel elementContainer;
    JLabel phoneImage;

    public NcsUiFactory(JPanel toolWindowContent) {
        this.toolWindowContent = toolWindowContent;
        this.bgLayout = new SpringLayout();
        this.elementContainer = new JPanel(bgLayout);
        this.phoneImage = new JLabel(new ImageIcon(getImage("phone.png")));
    }

    protected URL getImage(String imageName) {
        return getClass().getResource("/resources/ctap-simulator/images/" + imageName);
    }

    public void setToolWindowContent(JPanel toolWindowContent) {
        this.toolWindowContent = toolWindowContent;
    }


}