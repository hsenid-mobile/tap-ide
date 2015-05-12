package hms.tap.idea.plugin.file.templates.snippet;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by isuru on 4/19/15.
 */
public enum CodeSnippet {

    SMS("SmsRequestSender %s = new SmsRequestSender(new URL(\"http://127.0.0.1:7000/sms/send\"));", "smsRequestSender", Arrays.asList("hms.tap.api.sms.SmsRequestSender", "java.net.URL")),
    USSD("UssdRequestSender %s = new UssdRequestSender(new URL(\"http://127.0.0.1:7000/ussd/send\"));", "ussdRequestSender", Arrays.asList("hms.tap.api.ussd.UssdRequestSender", "java.net.URL")),
    SUBSCRIPTION("SubscriptionRequestSender %s = new SubscriptionRequestSender(new URL(\"http://127.0.0.1:7000/subscription/send\"));", "subscriptionRequestSender", Arrays.asList("hms.tap.api.subscription.SubscriptionRequestSender", "java.net.URL")),
    CAS("ChargingRequestSender %s = new ChargingRequestSender(new URL(\"http://127.0.0.1:7000/caas/direct/debit\"));", "chargingRequestSender", Arrays.asList("hms.tap.api.caas.ChargingRequestSender", "java.net.URL")),
    LBS("LbsRequestSender %s = new LbsRequestSender(new URL(\"http://127.0.0.1:7000/lbs/locate\"));", "lbsRequestSender", Arrays.asList("hms.tap.api.lbs.LbsRequestSender", "java.net.URL")),
    USSD_SESSION_PROCESSOR("UssdMessageProcessor %s = new UssdMessageProcessor.Builder().appConfig(new AppConfig.Builder().appId(\"APP_xxxxxx\").password(\"xxxxxx\").build()).menus(Arrays.<Menu>asList(new DefaultFirstMenu())).build();", "ussdMessageProcessor", Arrays.asList("hms.tap.ussd.manager.UssdMessageProcessor", "hms.tap.ussd.manager.conf.AppConfig", "hms.tap.ussd.manager.menu.DefaultFirstMenu", "hms.tap.ussd.manager.menu.Menu", "java.util.Arrays"));

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
