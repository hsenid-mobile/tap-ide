package hms.tap.idea.plugin.action.snippet;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.intellij.psi.search.GlobalSearchScope;
import hms.tap.idea.plugin.file.templates.snippet.CodeSnippet;
import hms.tap.idea.plugin.util.PsiUtil;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by isuru on 4/16/15.
 */
public abstract class CodeSnippetGeneratorAction extends AnAction {

    public CodeSnippetGeneratorAction(String text, String description, Icon icon) {
        super(text, description, icon);
    }

    public abstract CodeSnippet codeSnippet();

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        PsiClass psiClassFromContext = PsiUtil.getPsiClassFromContext(anActionEvent);
        PsiJavaFile psiJavaFile = PsiUtil.getPsiJavaFileFromContext(anActionEvent);
        addNewField(psiClassFromContext, codeSnippet().getClient(psiClassFromContext), codeSnippet().getImportList(), psiJavaFile);
    }

    @Override
    public void update(AnActionEvent e) {
        PsiClass psiClassFromContext = PsiUtil.getPsiClassFromContext(e);
        e.getPresentation().setEnabled(psiClassFromContext != null);
    }

    private void addNewField(final PsiClass psiClass, String fieldAsString, final List<String> imports, final PsiJavaFile psiJavaFile) {
        final PsiElementFactory elementFactory = JavaPsiFacade.getElementFactory(psiClass.getProject());
        final PsiField newField = elementFactory.createFieldFromText(fieldAsString, psiClass);


        WriteCommandAction.runWriteCommandAction(psiClass.getProject(), new Runnable() {
            @Override
            public void run() {
                PsiElement field = psiClass.add(newField);
                Project project = psiClass.getProject();
                JavaCodeStyleManager.getInstance(project).shortenClassReferences(field);

                for (String anImport : imports) {
                    GlobalSearchScope scope = GlobalSearchScope.allScope(project);
                    PsiClass importPsiClass = JavaPsiFacade.getInstance(project).findClass(anImport, scope);
                    if(importPsiClass != null) {
                        JavaCodeStyleManager.getInstance(project).addImport(psiJavaFile, importPsiClass);
                    }
                }

            }
        });
    }

}
