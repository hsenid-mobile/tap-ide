package hms.ctap.idea.plugin.ui;
import javax.swing.*;
import java.net.URL;

public class NcsUiFactory  {

        JTextArea displayTextArea;

        protected URL getImage(String imageName){
                return getClass().getResource("/resources/ctap-simulator/images/"+imageName);
        }
}