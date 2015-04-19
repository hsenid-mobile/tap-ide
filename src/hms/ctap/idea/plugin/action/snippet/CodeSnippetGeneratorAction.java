package hms.ctap.idea.plugin.action.snippet;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import hms.ctap.idea.plugin.file.templates.snippet.CodeSnippetUtil;
import hms.ctap.idea.plugin.util.PsiUtil;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by isuru on 4/16/15.
 */
public abstract class CodeSnippetGeneratorAction extends AnAction {

    public CodeSnippetGeneratorAction(String text, String description, Icon icon) {
        super(text, description, icon);
    }

    public abstract CodeSnippetUtil codeSnippetUtil();

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        PsiClass psiClassFromContext = PsiUtil.getPsiClassFromContext(anActionEvent);
        addNewField(psiClassFromContext, codeSnippetUtil().getClient(psiClassFromContext));
    }

    @Override
    public void update(AnActionEvent e) {
        PsiClass psiClassFromContext = PsiUtil.getPsiClassFromContext(e);
        e.getPresentation().setEnabled(psiClassFromContext != null);
    }

    protected void addNewField(final PsiClass psiClass, String fieldAsString) {
        PsiElementFactory elementFactory = JavaPsiFacade.getElementFactory(psiClass.getProject());
        final PsiField newField = elementFactory.createFieldFromText(fieldAsString, psiClass);
        WriteCommandAction.runWriteCommandAction(psiClass.getProject(), new Runnable() {
            @Override
            public void run() {
                PsiElement field = psiClass.add(newField);
                JavaCodeStyleManager.getInstance(psiClass.getProject()).shortenClassReferences(field);
            }
        });
    }

}
