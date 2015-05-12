package hms.tap.idea.plugin.util;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

/**
 * IconUtil - Loads the jetty icon
 * @author Gui Keller
 */
public class IconUtil {

    private static final IconUtil INSTANCE = new IconUtil();

    private IconUtil(){
        super();
    }

    public static IconUtil getInstance(){
        return INSTANCE;
    }

    public Icon getIcon(){
        return new ImageIcon(getClass().getResource("/resources/tap-runner/images/"+"runner-icon.png"));
    }

    public static class ServiceClassIcon {
        public static final Icon Service = IconLoader.getIcon("/hms/tap/idea/plugin/icons/service.png");
        public static final Icon SMS_MO = IconLoader.getIcon("/hms/tap/idea/plugin/icons/sms.png");
        public static final Icon SMS_DR = IconLoader.getIcon("/hms/tap/idea/plugin/icons/sms_notify.png");
        public static final Icon USSD_MO = IconLoader.getIcon("/hms/tap/idea/plugin/icons/ussd.png");
        public static final Icon CAS_SERVICE = IconLoader.getIcon("/hms/tap/idea/plugin/icons/cas.png");
        public static final Icon SUBSCRIPTION_NOTIFY = IconLoader.getIcon("/hms/tap/idea/plugin/icons/subscription.png");

        public static final Icon SMS = IconLoader.getIcon("/hms/tap/idea/plugin/icons/sms.png");
        public static final Icon USSD = IconLoader.getIcon("/hms/tap/idea/plugin/icons/ussd.png");
        public static final Icon USSD_MSG_PROCESSOR = IconLoader.getIcon("/hms/tap/idea/plugin/icons/ussd_msg_processor.png");
        public static final Icon LBS_SERVICE = IconLoader.getIcon("/hms/tap/idea/plugin/icons/lbs.png");
        public static final Icon SIMULATOR = IconLoader.getIcon("/hms/tap/idea/plugin/icons/simulator.png");
        public static final Icon SIMULATOR_LOADING = IconLoader.getIcon("/hms/tap/idea/plugin/icons/loading.png");
    }

}
