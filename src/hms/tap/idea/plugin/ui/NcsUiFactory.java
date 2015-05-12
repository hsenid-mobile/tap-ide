package hms.tap.idea.plugin.ui;
import javax.swing.*;
import javax.swing.JPanel;
import java.net.URL;

public class NcsUiFactory  {

        SpringLayout bgLayout;
        JTextArea displayTextArea;
        JPanel toolWindowContent;
        JPanel elementContainer;
        JLabel phoneImage;

        protected URL getImage(String imageName){
                return getClass().getResource("/resources/tap-simulator/images/"+imageName);
        }

        public  NcsUiFactory(JPanel toolWindowContent) {
                this.toolWindowContent = toolWindowContent;
                this.bgLayout = new SpringLayout();
                this.elementContainer = new JPanel(bgLayout);
                this.phoneImage = new JLabel(new ImageIcon(getImage("phone.png")));
        }

        public void setToolWindowContent(JPanel toolWindowContent) {
                this.toolWindowContent = toolWindowContent;
        }


}