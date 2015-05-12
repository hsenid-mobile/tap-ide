package hms.tap.idea.plugin.file.templates;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.impl.CustomFileTemplate;
import com.intellij.ide.fileTemplates.impl.UrlUtil;
import com.intellij.openapi.util.text.StringUtil;

import java.io.IOException;

/**
 * Created by isuru on 4/17/15.
 */
public class FileTemplates {

    public static final FileTemplate SMS_MO_LISTENER_TEMPLATE;
    public static final FileTemplate USSD_MO_LISTENER_TEMPLATE;
    public static final FileTemplate SMS_DR_LISTENER_TEMPLATE;
    public static final FileTemplate CAS_LISTENER_TEMPLATE;
    public static final FileTemplate SUBSCRIPTION_LISTENER_TEMPLATE;
    static {
        SMS_MO_LISTENER_TEMPLATE = new CustomFileTemplate("sms_mo_listener", "java");
        USSD_MO_LISTENER_TEMPLATE = new CustomFileTemplate("ussd_mo_listener", "java");
        SMS_DR_LISTENER_TEMPLATE = new CustomFileTemplate("dr_mo_listener", "java");
        CAS_LISTENER_TEMPLATE = new CustomFileTemplate("cas_listener", "java");
        SUBSCRIPTION_LISTENER_TEMPLATE = new CustomFileTemplate("subscription_listener", "java");

        try {
            SMS_MO_LISTENER_TEMPLATE.setText(StringUtil.convertLineSeparators(UrlUtil.loadText(FileTemplates.class.getResource("/hms/tap/idea/plugin/file/templates/sms_mo_listener.ft"))));
            USSD_MO_LISTENER_TEMPLATE.setText(StringUtil.convertLineSeparators(UrlUtil.loadText(FileTemplates.class.getResource("/hms/tap/idea/plugin/file/templates/ussd_mo_listener.ft"))));
            SMS_DR_LISTENER_TEMPLATE.setText(StringUtil.convertLineSeparators(UrlUtil.loadText(FileTemplates.class.getResource("/hms/tap/idea/plugin/file/templates/dr_listener.ft"))));
            CAS_LISTENER_TEMPLATE.setText(StringUtil.convertLineSeparators(UrlUtil.loadText(FileTemplates.class.getResource("/hms/tap/idea/plugin/file/templates/cas_listener.ft"))));
            SUBSCRIPTION_LISTENER_TEMPLATE.setText(StringUtil.convertLineSeparators(UrlUtil.loadText(FileTemplates.class.getResource("/hms/tap/idea/plugin/file/templates/subscription_listener.ft"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
