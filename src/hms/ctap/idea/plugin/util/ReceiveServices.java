package hms.ctap.idea.plugin.util;

import com.intellij.ide.fileTemplates.FileTemplate;
import hms.ctap.idea.plugin.file.templates.FileTemplates;

import javax.swing.*;

import java.util.Arrays;
import java.util.List;

import static hms.ctap.idea.plugin.util.IconUtil.ServiceClassIcon.*;
import static hms.ctap.idea.plugin.util.MessageUtil.message;

/**
 * Created by isuru on 4/17/15.
 */
public enum ReceiveServices {

    SMS_M0(SMS_MO, message("ctap.receive.service.sms.mo.text"), message("ctap.receive.service.sms.mo.description"), "smsMoReceiver", "smsMoReceiver", "MoClass", "smsReceiver", FileTemplates.SMS_MO_RECEIVER_TEMPLATE),
    USSD_M0(USSD_MO, message("ctap.receive.service.ussd.mo.text"), message("ctap.receive.service.ussd.mo.description"), "ussdMoReceiver", "ussdMoReceiver", "UssdClass", "ussdReceiver", FileTemplates.USSD_MO_RECEIVER_TEMPLATE);

    private final Icon icon;
    private final String text;
    private final String description;
    private final String servletPath;
    private final String servletName;
    private final String servletClass;
    private final String initParamName;
    private final FileTemplate template;

    ReceiveServices(Icon icon, String text, String description, String servletPath, String servletName, String servletClass, String initParamName, FileTemplate template) {

        this.icon = icon;
        this.text = text;
        this.description = description;
        this.servletPath = servletPath;
        this.servletName = servletName;
        this.servletClass = servletClass;
        this.initParamName = initParamName;
        this.template = template;
    }

    public Icon icon() {
        return icon;
    }

    public String text() {
        return text;
    }

    public String description() {
        return description;
    }

    public String servletPath() {
        return servletPath;
    }

    public String servletName() {
        return servletName;
    }

    public FileTemplate template() {
        return template;
    }

    public String servletClass() {
        return servletClass;
    }

    public String initParamName() {
        return initParamName;
    }

    public static final List<ReceiveServices> receiveServices = Arrays.asList(ReceiveServices.values());
}
