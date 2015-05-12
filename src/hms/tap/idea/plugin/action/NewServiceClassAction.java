package hms.tap.idea.plugin.action;

import com.intellij.ide.IdeView;
import com.intellij.ide.actions.CreateElementActionBase;
import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.TemplateKindCombo;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.*;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScopesCore;
import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;
import com.intellij.ui.DocumentAdapter;
import com.intellij.util.IncorrectOperationException;
import hms.tap.idea.plugin.file.templates.servlet.ServletIdUtil;
import hms.tap.idea.plugin.ui.ErrorText;
import hms.tap.idea.plugin.util.IconUtil.ServiceClassIcon;
import hms.tap.idea.plugin.util.ReceiveListeners;
import hms.tap.idea.plugin.file.templates.servlet.WebXmlUtil;
import hms.tap.idea.plugin.util.MessageUtil;
import hms.tap.idea.plugin.util.ReceiveListeners;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.java.JavaModuleSourceRootTypes;
import hms.tap.idea.plugin.file.templates.servlet.WebXmlUtil;

import javax.swing.*;
import javax.swing.event.DocumentEvent;

import static hms.tap.idea.plugin.util.MessageUtil.message;
import static hms.tap.idea.plugin.util.ReceiveListeners.RECEIVE_SERVICEs;

/**
 * Created by isuru on 4/16/15.
 */
public class NewServiceClassAction extends CreateElementActionBase {

    private String serviceClassName = null;
    private String serviceType = null;
    private String servletPath = null;

    public NewServiceClassAction() {
        super(MessageUtil.message("tap.receive.services.text"), MessageUtil.message("tap.receive.services.description"), ServiceClassIcon.Service);
    }

    @Override
    public void update(AnActionEvent e) {
        super.update(e);
        final Project project = e.getData(CommonDataKeys.PROJECT);
        final Presentation presentation = e.getPresentation();
        if (presentation.isEnabled()) {
            final IdeView view = e.getData(LangDataKeys.IDE_VIEW);
            if (view != null) {
                final ProjectFileIndex projectFileIndex = ProjectRootManager.getInstance(project).getFileIndex();
                final PsiDirectory[] dirs = view.getDirectories();
                for (final PsiDirectory dir : dirs) {
                    if (projectFileIndex.isUnderSourceRootOfType(dir.getVirtualFile(), JavaModuleSourceRootTypes.SOURCES) && JavaDirectoryService.getInstance().getPackage(dir) != null) {
                        return;
                    }
                }
            }

            presentation.setEnabled(false);
            presentation.setVisible(false);
        }
    }

    @NotNull
    @Override
    protected PsiElement[] invokeDialog(Project project, PsiDirectory psiDirectory) {
        final CreateElementActionBase.MyInputValidator validator = new JavaNameValidator(project, psiDirectory);

        final DialogWrapper dialog = new MyDialog(project, validator);
        dialog.show();

        return validator.getCreatedElements();
    }

    @NotNull
    @Override
    protected PsiElement[] create(String s, PsiDirectory psiDirectory) throws Exception {
        final ReceiveListeners receiveService = ReceiveListeners.valueOf(serviceType);
        PreProcessResult preProcessResult = preProcessFileName(s, psiDirectory);
        final PsiFile psiFile = CreateFileFromTemplateAction.createFileFromTemplate(preProcessResult.getNewName(), receiveService.template(), preProcessResult.getPsiDirectory(), null);

        try {
            Project project = psiDirectory.getProject();
            PsiFile[] webXmlFilesByName = FilenameIndex.getFilesByName(project, "web.xml", GlobalSearchScopesCore.directoryScope(project, project.getBaseDir(), true));
            final XmlFile webXmlFile = (XmlFile) webXmlFilesByName[0];

            final XmlFile tempXmlFile = (XmlFile)PsiFileFactory.getInstance(project).createFileFromText("temp.xml", StdFileTypes.XML, WebXmlUtil.servletEntry());

            WriteCommandAction.runWriteCommandAction(project, new Runnable() {
                @Override
                public void run() {
                    // Servlet XML tag
                    servletXMLTag(tempXmlFile, webXmlFile, receiveService, psiFile);
                }
            });

            tempXmlFile.delete();
        } catch (Exception e) {}

        return new PsiElement[] {psiFile};
    }

    private void servletXMLTag(XmlFile tempXmlFile, XmlFile webXmlFile, ReceiveListeners receiveService, PsiFile psiFile) {
        XmlTag servletXmlTag = tempXmlFile.getRootTag().getSubTags()[0];
        String servletName = ServletIdUtil.getAvailableServletName(webXmlFile, receiveService.servletName());
        servletXmlTag.getSubTags()[0].getValue().setText(servletName);
        servletXmlTag.getSubTags()[1].getValue().setText(receiveService.servletClass());
        servletXmlTag.getSubTags()[2].getSubTags()[0].getValue().setText(receiveService.initParamName());
        PsiClass psiClass = null;
        for (PsiElement psiElement : psiFile.getChildren()) {
            if(psiElement instanceof PsiClass) {
                psiClass = (PsiClass)psiElement;
            }
        }
        servletXmlTag.getSubTags()[2].getSubTags()[1].getValue().setText(psiClass.getQualifiedName());
        webXmlFile.getRootTag().addSubTag(tempXmlFile.getRootTag().getSubTags()[0], false);

        XmlTag servletMappingXmlTag = tempXmlFile.getRootTag().getSubTags()[1];
        servletMappingXmlTag.getSubTags()[0].getValue().setText(servletName);
        servletMappingXmlTag.getSubTags()[1].getValue().setText(ServletIdUtil.getAvailableServletPath(webXmlFile, receiveService.servletPath()));
        webXmlFile.getRootTag().addSubTag(tempXmlFile.getRootTag().getSubTags()[1], false);
    }

    private PreProcessResult preProcessFileName(String newName, PsiDirectory directory) throws IncorrectOperationException {

        newName = removeExtension(newName);

        if (newName.contains(".")) {
            String[] names = newName.split("\\.");

            for (int i = 0; i < names.length - 1; i++) {
                String name = names[i];
                PsiDirectory subDir = directory.findSubdirectory(name);

                if (subDir == null) {
                    subDir = directory.createSubdirectory(name);
                }

                directory = subDir;
            }

            newName = names[names.length - 1];
        }
        return new PreProcessResult(newName, directory);
    }

    protected String removeExtension(String className) {
        return StringUtil.trimEnd(className, ".java");
    }

    public static final class PreProcessResult {
        private final String newName;
        private final PsiDirectory psiDirectory;

        public PreProcessResult(String newName, PsiDirectory psiDirectory) {
            this.newName = newName;
            this.psiDirectory = psiDirectory;
        }

        public String getNewName() {
            return newName;
        }

        public PsiDirectory getPsiDirectory() {
            return psiDirectory;
        }
    }

    @Override
    protected String getErrorTitle() {
        return "Cannot create NCS";
    }

    @Override
    protected String getCommandName() {
        return "Create NCS";
    }

    @Override
    protected String getActionName(PsiDirectory psiDirectory, String s) {
        PsiPackage aPackage = JavaDirectoryService.getInstance().getPackage(psiDirectory);
        String fqName = aPackage != null ? aPackage.getQualifiedName() + "." + s : s;
        return "Creating class " + fqName;
    }

    private class MyDialog extends DialogWrapper {
        private JPanel myTopPanel;
        private JTextField listenerClassNameTextField;
        private TemplateKindCombo serviceTypeComboBox;
        private JLabel errorTextLabel;
        private ErrorText errorText;


        private final Project myProject;
        private final CreateElementActionBase.MyInputValidator myValidator;

        public MyDialog(final Project project,
                        final CreateElementActionBase.MyInputValidator validator) {
            super(project, true);
            myProject = project;
            myValidator = validator;
            init();

            errorText = new ErrorText(errorTextLabel);
            setTitle(MessageUtil.message("tap.receive.services.wizard.title"));
            setOKActionEnabled(false);

            listenerClassNameTextField.getDocument().addDocumentListener(new DocumentAdapter() {
                protected void textChanged(DocumentEvent e) {
                    setOKActionEnabled(listenerClassNameTextField.getText().length() > 0);
                    boolean isValidName = myValidator.checkInput(listenerClassNameTextField.getText());
                    if(isValidName) {
                        setOKActionEnabled(true);
                        errorText.setError(null);
                    }
                }
            });

            for (ReceiveListeners s : ReceiveListeners.RECEIVE_SERVICEs) {
                serviceTypeComboBox.addItem(s.text(), s.icon(), s.name());
            }

            serviceTypeComboBox.setSelectedName(ReceiveListeners.RECEIVE_SERVICEs.get(0).name());
        }

        protected JComponent createCenterPanel() {
            return myTopPanel;
        }

        protected void doOKAction() {
            final String inputString = listenerClassNameTextField.getText().trim();
            serviceClassName = listenerClassNameTextField.getText();
            serviceType = serviceTypeComboBox.getSelectedName();

            if (myValidator.checkInput(inputString) && myValidator.canClose(inputString)) {
                close(OK_EXIT_CODE);
            } else {
                errorText.setError(MessageUtil.message("tap.create.class.incorrect.name"));
            }
            //close(OK_EXIT_CODE);
        }

        public JComponent getPreferredFocusedComponent() {
            return listenerClassNameTextField;
        }

        private void createUIComponents() {
            // TODO: place custom component creation code here
        }
    }
    protected class JavaNameValidator extends CreateElementActionBase.MyInputValidator {
        private final Project myProject;

        public JavaNameValidator(Project project, PsiDirectory directory) {
            super(project, directory);
            myProject = project;
        }

        @Override
        public boolean checkInput(String inputString) {
            return inputString.length() > 0 && PsiNameHelper.getInstance(myProject).isQualifiedName(inputString);
        }
    }
}
