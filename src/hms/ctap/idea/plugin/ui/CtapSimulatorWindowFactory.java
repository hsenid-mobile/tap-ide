package hms.ctap.idea.plugin.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: Alexey.Chursin
 * Date: Aug 25, 2010
 * Time: 2:09:00 PM
 */
public class CtapSimulatorWindowFactory implements ToolWindowFactory {


    private JButton hideToolWindowButton;
    private JLabel logo;
    private JPanel toolWindowContent;
    private ToolWindow toolWindow;


    public CtapSimulatorWindowFactory() {
        hideToolWindowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toolWindow.hide(null);
            }
        });
    }

    // Create the tool window content.
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        this.toolWindow = toolWindow;
        this.currentDateTime();
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(toolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content);

    }


    public void currentDateTime() {
        logo.setIcon(new ImageIcon(getClass().getResource("/hms/ctap/idea/plugin/ui/Dev_Space_logo.png")));
    }

}
