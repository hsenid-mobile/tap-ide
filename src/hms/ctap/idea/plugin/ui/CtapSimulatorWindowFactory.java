package hms.ctap.idea.plugin.ui;

import com.google.common.base.Optional;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

import java.lang.System;
import java.net.URL;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.text.Document;
import java.awt.*;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import hms.ctap.idea.plugin.simulator.SimulatorServer;
import hms.ctap.idea.plugin.ui.UssdUiFactory;
import hms.ctap.idea.plugin.ui.SmsUiFactory;

public class CtapSimulatorWindowFactory implements ToolWindowFactory {

    private JPanel toolWindowContent;
    private ToolWindow toolWindow;

    public static final String SMS_UI_FACTORY="SmsUiFactory";
    private static final Map<String,Object> intializedUIs = new HashMap<String, Object>();

    public CtapSimulatorWindowFactory() {
/*        UssdUiFactory ussdUiFactory = new UssdUiFactory(this.toolWindowContent);
        this.toolWindowContent = ussdUiFactory.createInitialUI();*/

        SmsUiFactory smsUiFactory = new SmsUiFactory(this.toolWindowContent);
        this.toolWindowContent = smsUiFactory.createInitialUI();
        intializedUIs.put(SMS_UI_FACTORY,smsUiFactory);
        //SimulatorServer simulatorServer = new SimulatorServer();
        //simulatorServer.start();


    }

    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        this.toolWindow = toolWindow;
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(toolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content);

    }

    public void setToolWindowContent(JPanel toolWindowContent){
        this.toolWindowContent = toolWindowContent;
    }

    public static Object getInitializedUIObject(String name){
        return  intializedUIs.get(name);
    }

}
