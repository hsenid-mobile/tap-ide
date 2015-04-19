package hms.ctap.idea.plugin.file.templates;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.impl.CustomFileTemplate;
import com.intellij.ide.fileTemplates.impl.UrlUtil;
import com.intellij.openapi.util.text.StringUtil;

import java.io.IOException;

/**
 * Created by isuru on 4/17/15.
 */
public class FileTemplates {

    public static final FileTemplate SMS_MO_RECEIVER_TEMPLATE;
    public static final FileTemplate USSD_MO_RECEIVER_TEMPLATE;
    static {
        SMS_MO_RECEIVER_TEMPLATE = new CustomFileTemplate("sms_mo_receiver", "java");
        USSD_MO_RECEIVER_TEMPLATE = new CustomFileTemplate("ussd_mo_receiver", "java");
        try {
            SMS_MO_RECEIVER_TEMPLATE.setText(StringUtil.convertLineSeparators(UrlUtil.loadText(FileTemplates.class.getResource("/hms/ctap/idea/plugin/file/templates/sms_mo_receiver.ft"))));
            USSD_MO_RECEIVER_TEMPLATE.setText(StringUtil.convertLineSeparators(UrlUtil.loadText(FileTemplates.class.getResource("/hms/ctap/idea/plugin/file/templates/ussd_mo_receiver.ft"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
