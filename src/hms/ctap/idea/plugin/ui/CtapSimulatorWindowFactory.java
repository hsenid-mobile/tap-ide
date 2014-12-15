package hms.ctap.idea.plugin.ui;

import com.ctap.simulator.SimulatorServer;
import com.ctap.simulator.SmsNcsRestService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class CtapSimulatorWindowFactory implements ToolWindowFactory {

    public static final String SMS_UI_FACTORY = "SmsUiFactory";
    private static final Map<String, Object> intializedUIs = new HashMap<String, Object>();
    private JPanel toolWindowContent;
    private ToolWindow toolWindow;

    public CtapSimulatorWindowFactory() {
/*        UssdUiFactory ussdUiFactory = new UssdUiFactory(this.toolWindowContent);
        this.toolWindowContent = ussdUiFactory.createInitialUI();*/

        SmsUiFactory smsUiFactory = new SmsUiFactory(this.toolWindowContent);
        this.toolWindowContent = smsUiFactory.createInitialUI();
        intializedUIs.put(SMS_UI_FACTORY, smsUiFactory);
        SimulatorServer simulatorServer = new SimulatorServer();
        simulatorServer.start(null);

        while (true) {

            try {
                Thread.sleep(1000);
                String message = SmsNcsRestService.removeMessage();
                smsUiFactory.createMsgReceivedUI(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public static Object getInitializedUIObject(String name) {
        return intializedUIs.get(name);
    }

    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        this.toolWindow = toolWindow;
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(toolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content);

    }

    public void setToolWindowContent(JPanel toolWindowContent) {
        this.toolWindowContent = toolWindowContent;
    }

}
