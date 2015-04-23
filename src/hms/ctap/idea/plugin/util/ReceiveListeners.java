package hms.ctap.idea.plugin.util;

import com.intellij.ide.fileTemplates.FileTemplate;
import hms.ctap.idea.plugin.file.templates.FileTemplates;
import hms.ctap.idea.plugin.util.IconUtil.ServiceClassIcon;

import javax.swing.*;

import java.util.Arrays;
import java.util.List;

import static hms.ctap.idea.plugin.util.MessageUtil.message;

/**
 * Created by isuru on 4/17/15.
 */
public enum ReceiveListeners {

    SMS_MO(ServiceClassIcon.SMS_MO, message("ctap.receive.service.sms.mo.text"), message("ctap.receive.service.sms.mo.description"), "/sms", "smsMoReceiver", "hms.tap.api.sms.MoSmsReceiver", "smsReceiver", FileTemplates.SMS_MO_LISTENER_TEMPLATE),
    SMS_DR(ServiceClassIcon.SMS_DR, message("ctap.receive.service.sms.dr.text"), message("ctap.receive.service.sms.dr.description"), "/sms-dr", "smsDrReceiver", "hms.tap.api.sms.MoSmsDeliveryReportReceiver", "smsDeReportReceiver", FileTemplates.SMS_DR_LISTENER_TEMPLATE),
    USSD_M0(ServiceClassIcon.USSD_MO, message("ctap.receive.service.ussd.mo.text"), message("ctap.receive.service.ussd.mo.description"), "/ussd", "ussdReceiver", "hms.tap.api.ussd.MoUssdReceiver", "ussdReceiver", FileTemplates.USSD_MO_LISTENER_TEMPLATE),
    SUBSCRIPTION(ServiceClassIcon.SUBSCRIPTION_NOTIFY, message("ctap.receive.service.subscription.notify.text"), message("ctap.receive.service.subscription.notify.description"), "/subscription", "subscriptionReceiver", "hms.tap.api.subscription.SubscriptionNotificationReceiver", "subReceiver", FileTemplates.SUBSCRIPTION_LISTENER_TEMPLATE),
    CAS(ServiceClassIcon.CAS_SERVICE, message("ctap.receive.service.cas.notify.text"), message("ctap.receive.service.cas.notify.description"), "/cas", "casReceiver", "hms.tap.api.caas.ChargingNotificationReceiver", "chargingNotificationReceive", FileTemplates.CAS_LISTENER_TEMPLATE);


    private final Icon icon;
    private final String text;
    private final String description;
    private final String servletPath;
    private final String servletName;
    private final String servletClass;
    private final String initParamName;
    private final FileTemplate template;

    ReceiveListeners(Icon icon, String text, String description, String servletPath, String servletName, String servletClass, String initParamName, FileTemplate template) {

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

    public static final List<ReceiveListeners> RECEIVE_SERVICEs = Arrays.asList(ReceiveListeners.values());
}
