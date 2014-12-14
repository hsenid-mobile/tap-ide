package hms.ctap.idea.plugin.runner.ui;

import hms.ctap.idea.plugin.runner.model.CtapRunnerConfiguration;
import com.intellij.compiler.impl.ModuleCompileScope;
import com.intellij.openapi.compiler.CompileContext;
import com.intellij.openapi.compiler.CompileTask;
import com.intellij.openapi.compiler.CompilerManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.PsiShortNamesCache;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.UUID;

/**
 * Controller - Jetty Runner Editor
 * @see com.intellij.openapi.options.SettingsEditor
 * @author Gui Keller
 */
public class CtapRunnerEditor extends SettingsEditor<CtapRunnerConfiguration> {

    private CtapRunnerConfPanel configurationPanel;
    private String mainOutputDirectory;

    public CtapRunnerEditor(CtapRunnerConfiguration ctapRunnerConfiguration) {
        this.configurationPanel = new CtapRunnerConfPanel();
        super.resetFrom(ctapRunnerConfiguration);
    }

    /**
     * This is invoked when the form is first loaded.
     * The values may be stored in disk, if not, set some defaults
     * @param ctapRunnerConfiguration ctapRunnerConfiguration
     */
    @Override
    protected void resetEditorFrom(CtapRunnerConfiguration ctapRunnerConfiguration) {
        Project project = ctapRunnerConfiguration.getProject();
        // WebApp Path
        if (ctapRunnerConfiguration.getWebappPaths() != null) {
            this.configurationPanel.getPathField().setText(ctapRunnerConfiguration.getWebappPaths());
        } else {
            String projectName = project.getName();
            this.configurationPanel.getPathField().setText("/"+projectName);
        }
        // WebApp Folder (one level down to web.xml"
        if (ctapRunnerConfiguration.getWebappFolders() != null) {
            this.configurationPanel.getWebappField().setText(ctapRunnerConfiguration.getWebappFolders());
        } else {
            String webAppsFolder = getWebAppsFolder(project);
            this.configurationPanel.getWebappField().setText(webAppsFolder);
        }
        // Classes directory
        if (ctapRunnerConfiguration.getClassesDirectories() != null) {
            this.configurationPanel.getClassesField().setText(ctapRunnerConfiguration.getClassesDirectories());
        } else {
            String outputDirectory = getMainOutputDirectory(project);
            this.configurationPanel.getClassesField().setText(outputDirectory);
        }
        // Runs on port
        if (ctapRunnerConfiguration.getRunningOnPort() != null) {
            this.configurationPanel.getRunOnPortField().setText(ctapRunnerConfiguration.getRunningOnPort());
        } else {
            this.configurationPanel.getRunOnPortField().setText("8080");
        }
        // Jetty XML (Optional)
        this.configurationPanel.getXmlField().setText(ctapRunnerConfiguration.getJettyXml());
        // Vm Args (Optional)
        this.configurationPanel.getVmArgsField().setText(ctapRunnerConfiguration.getVmArgs());
    }

    /**
     * This is invoked when the user fills the form and pushes apply/ok
     * @param ctapRunnerConfiguration ctapRunnerConfiguration
     * @throws com.intellij.openapi.options.ConfigurationException ex
     */
    @Override
    protected void applyEditorTo(CtapRunnerConfiguration ctapRunnerConfiguration) throws ConfigurationException {
        ctapRunnerConfiguration.setWebappPaths(this.configurationPanel.getPathField().getText());
        ctapRunnerConfiguration.setWebappFolders(this.configurationPanel.getWebappField().getText());
        ctapRunnerConfiguration.setClassesDirectories(this.configurationPanel.getClassesField().getText());
        ctapRunnerConfiguration.setRunningOnPort(this.configurationPanel.getRunOnPortField().getText());
        ctapRunnerConfiguration.setJettyXml(this.configurationPanel.getXmlField().getText());
        ctapRunnerConfiguration.setVmArgs(this.configurationPanel.getVmArgsField().getText());
        try {
            // Not entirely sure if 'I have' to do this - the IntelliJ framework may do
            ctapRunnerConfiguration.writeExternal(new Element(CtapRunnerConfiguration.PREFIX + UUID.randomUUID().toString()));
        } catch (WriteExternalException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return this.configurationPanel.getMainPanel();
    }

    // Helpers

    /**
     * Retrieves the output directory for the main module
     * @param project Project
     * @return String value
     */
    private String getMainOutputDirectory(Project project) {
        // Preparing things up for a sneaky "CompileTask"
        final CompilerManager compilerManager = CompilerManager.getInstance(project);
        final Module[] modules = ModuleManager.getInstance(project).getModules();
        final ModuleCompileScope compileScope = new ModuleCompileScope(project, modules, false);
        final Module mainModule = modules[0];
        // Though a "CompileTask" I can get hold of the "CompileContext"
        CompileTask compileTask = new CompileTask() {
            public boolean execute(CompileContext compileContext) {
                // Through the "CompileContext" I can get the output directory of the main module
                VirtualFile mainOutputDirectory = compileContext.getModuleOutputDirectory(mainModule);
                CtapRunnerEditor.this.mainOutputDirectory = mainOutputDirectory.getPresentableUrl();
                return true;
            }
        };
        // Executes the task (synchronously), which invokes that internal 'execute' method
        compilerManager.executeTask(compileTask, compileScope, "JettyRunner-By-GuiKeller", null);
        return this.mainOutputDirectory;
    }

    /**
     * Returns the most probable WebApps folder
     * @param project Project
     * @return String value
     */
    private String getWebAppsFolder(Project project) {
        // Using the api to look for the web.xml
        PsiShortNamesCache namesCache = PsiShortNamesCache.getInstance(project);
        PsiFile[] webXML = namesCache.getFilesByName("web.xml");
        if (webXML == null || webXML.length < 1) return "";
        // Grab the first one that the api found
        PsiFile file = webXML[0];
        // The parent folder is the "WEB-INF" folder
        PsiDirectory webInfFolder = file.getParent();
        if (webInfFolder == null) return "";
        // The parent folder to "WEB-INF" is the WebApps folder
        PsiDirectory webappFolder = webInfFolder.getParent();
        if (webappFolder == null) return "";
        // Folder found, returns it to the user
        VirtualFile virtualFile = webappFolder.getVirtualFile();
        return virtualFile.getPresentableUrl();
    }

    public void setConfigurationPanel(CtapRunnerConfPanel configurationPanel) {
        this.configurationPanel = configurationPanel;
    }
}
