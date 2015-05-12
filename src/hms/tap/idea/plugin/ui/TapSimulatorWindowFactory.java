package hms.tap.idea.plugin.ui;

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
import hms.tap.idea.plugin.ui.UssdUiFactory;

public class TapSimulatorWindowFactory implements ToolWindowFactory {

    private JPanel toolWindowContent;
    private ToolWindow toolWindow;

    public TapSimulatorWindowFactory() {
         UssdUiFactory ussdUiFactory = new UssdUiFactory(this.toolWindowContent);
         this.toolWindowContent = ussdUiFactory.createInitialUI();
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

}
