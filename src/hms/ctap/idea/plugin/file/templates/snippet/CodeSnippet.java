package hms.ctap.idea.plugin.file.templates.snippet;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by isuru on 4/19/15.
 */
public enum CodeSnippet {

    LBS("LbsClient %s = new LbsClient(\"127.0.0.\");", "lbsClient", Arrays.asList("ctap.import1.import.LbsClient", "hms.kite.samples.api.ussd.MoUssdListener")),
    SMS("SmsClient %s = new SmsClient(\"127.0.0.\");", "smsClient", Arrays.asList("ctap.import1.import.SmsClient", "ctap.import2.import.SmsClient"));

    private final String fieldBodyTemplate;
    private final String fieldId;
    private final List<String> importList;

    CodeSnippet(String fieldBodyTemplate, String fieldId, List<String> importList) {
        this.fieldBodyTemplate = fieldBodyTemplate;
        this.fieldId = fieldId;
        this.importList = importList;
    }

    public String getClient(PsiClass psiClass) {
        return String.format(fieldBodyTemplate, newFieldName(psiClass, fieldId));
    }

    public List<String> getImportList() {
        return importList;
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
