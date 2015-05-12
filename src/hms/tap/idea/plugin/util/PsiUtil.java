package hms.tap.idea.plugin.util;

import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NonNls;

/**
 * Created by isuru on 4/16/15.
 */
public class PsiUtil {
    public static PsiClass getPsiClassFromContext(AnActionEvent e) {
        PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if (psiFile == null || editor == null) {
            return null;
        }
        int offset = editor.getCaretModel().getOffset();
        PsiElement elementAt = psiFile.findElementAt(offset);
        return PsiTreeUtil.getParentOfType(elementAt, PsiClass.class);
    }

    public static PsiJavaFile getPsiJavaFileFromContext(AnActionEvent e) {
       return (PsiJavaFile)e.getData(LangDataKeys.PSI_FILE);
    }

    public static PsiMethod getPsiMethodFromContext(AnActionEvent e) {
        PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if (psiFile == null || editor == null) {
            return null;
        }
        int offset = editor.getCaretModel().getOffset();
        PsiElement elementAt = psiFile.findElementAt(offset);
        return PsiTreeUtil.getParentOfType(elementAt, PsiMethod.class);
    }

    public static PsiImportStatementBase psiImportStatement(String fqn, Project project) {
        PsiJavaFile aFile = createDummyJavaFile("import static " + fqn + ";", project);
        PsiImportStatementBase statement = extractImport(aFile, false);
        return statement;
    }

    private static PsiImportStatementBase extractImport(PsiJavaFile aFile, boolean isStatic) {
        PsiImportList importList = aFile.getImportList();

        assert importList != null : aFile;

        Object statements = isStatic?importList.getImportStaticStatements():importList.getImportStatements();

        assert ((Object[])statements).length == 1 : aFile.getText();

        return (PsiImportStatementBase)((Object[])statements)[0];
    }

    protected static PsiJavaFile createDummyJavaFile(@NonNls String text, Project project) {
        JavaFileType type = JavaFileType.INSTANCE;
        return (PsiJavaFile)PsiFileFactory.getInstance(project).createFileFromText( "_Dummy_." + JavaFileType.INSTANCE.getDefaultExtension(), type, text);
    }

}
