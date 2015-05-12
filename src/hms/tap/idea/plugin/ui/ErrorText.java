package hms.tap.idea.plugin.ui;

import com.intellij.icons.AllIcons;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.JBColor;
import com.intellij.xml.util.XmlStringUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Created by isuru on 4/18/15.
 */
public class ErrorText {

    private JLabel myLabel;

    public ErrorText(JLabel label) {
        this.myLabel = label;
    }

    public void setError(String text) {
        if (text == null) {
            myLabel.setText("");
            myLabel.setIcon(null);
        } else {
            myLabel
                    .setText(XmlStringUtil.wrapInHtml("<font color='#" + ColorUtil.toHex(JBColor.RED) + "'><left>" + text + "</left></b></font>"));
            myLabel.setIcon(AllIcons.Actions.Lightning);
            myLabel.setBorder(new EmptyBorder(4, 10, 0, 2));
        }
    }

}
