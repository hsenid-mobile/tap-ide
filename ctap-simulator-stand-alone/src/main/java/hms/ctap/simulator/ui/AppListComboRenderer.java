package hms.ctap.simulator.ui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by oshan on 1/16/15.
 */
public class AppListComboRenderer implements ListCellRenderer{
    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        list.setSelectionBackground(Color.WHITE);
        JLabel label = new JLabel();
        label.setOpaque(true);

        String[] splitedValues = value.toString().split("\\$\\$");
        System.out.println(splitedValues[0]+"|"+splitedValues[1]);
        if (splitedValues[0].equals("SMS")){
            label.setIcon(new ImageIcon(getImage("sms.png")));
        } else {
            label.setIcon(new ImageIcon(getImage("ussd.png")));
        }
        label.setText(splitedValues[1]);
        return label;
    }

    private URL getImage(String imageName) {
        return getClass().getResource("/ctap-simulator/images/" + imageName);
    }
}
