package hms.ctap.idea.plugin.file.templates.snippet;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;

import java.util.ArrayList;

/**
 * Created by isuru on 4/19/15.
 */
public enum CodeSnippetUtil {

    LBS("LbsClient %s = new LbsClient(\"127.0.0.\");", "lbsClient"),
    SMS("SmsClient %s = new SmsClient(\"127.0.0.\");", "smsClient");

    private final String fieldBodyTemplate;
    private final String fieldId;

    CodeSnippetUtil(String fieldBodyTemplate, String fieldId) {
        this.fieldBodyTemplate = fieldBodyTemplate;
        this.fieldId = fieldId;
    }

    public String getClient(PsiClass psiClass) {
        return String.format(fieldBodyTemplate, newFieldName(psiClass, fieldId));
    }

    private static String newFieldName(PsiClass psiClass, String fieldName) {
        PsiField[] fields = psiClass.getAllFields();

        ArrayList<String> startsWithFieldName = new ArrayList<String>();

        for (PsiField field : fields) {
            if(field.getName().startsWith(fieldName)) {
                startsWithFieldName.add(field.getName());
            }
        }

        int i = 1;
        String newFieldName = fieldName;
        while (true) {
            if(!startsWithFieldName.contains(newFieldName)) {
                break;
            }
            newFieldName = fieldName + i++;
        }
        return newFieldName;


    }
}
