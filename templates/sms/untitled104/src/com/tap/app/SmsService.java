#if (${IJ_BASE_PACKAGE} && ${IJ_BASE_PACKAGE} != "")package ${IJ_BASE_PACKAGE};#end

/**
 * Created by ${USER} on ${DATE}.
 */

import com.google.common.collect.Lists;

import hms.tap.api.TapException;
import hms.tap.api.sms.MoSmsListener;
import hms.tap.api.sms.SmsRequestSender;
import hms.tap.api.sms.messages.MoSmsReq;
import hms.tap.api.sms.messages.MtSmsReq;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

#if (${IJ_BASE_PACKAGE} && ${IJ_BASE_PACKAGE} != "")import static ${IJ_BASE_PACKAGE}.ConfigUtil.*;#end

public class SmsService implements MoSmsListener{

    private static final String welcomMsg =#if (!${IJ_BASE_PACKAGE} || ${IJ_BASE_PACKAGE} == "") ConfigUtil.getDisplayMessage("welcome.message").or("Welcome.");#else getDisplayMessage("welcome.message").or("Welcome.");#end

    private static final String appId = #if (!${IJ_BASE_PACKAGE} || ${IJ_BASE_PACKAGE} == "") ConfigUtil.getApplicationConfig("app.id");#else getApplicationConfig("app.id");#end
    private static final String password = #if (!${IJ_BASE_PACKAGE} || ${IJ_BASE_PACKAGE} == "") ConfigUtil.getApplicationConfig("app.password");#else getApplicationConfig("app.password");#end

    private static final Logger logger = Logger.getLogger("logger");

    @Override
    public void init() {
    }

    @Override
    public void onReceivedSms(MoSmsReq moSmsReq) {
        //
        try {
            String smsMoUrl =#if (!${IJ_BASE_PACKAGE} || ${IJ_BASE_PACKAGE} == "") ConfigUtil.getApplicationConfig("tap.sms.endpoint");#else getApplicationConfig("tap.sms.endpoint");#end

            SmsRequestSender smsRequestSender = new SmsRequestSender(new URL(smsMoUrl));
            final MtSmsReq mtSmsReq = getMtSmsReq(moSmsReq);
            smsRequestSender.sendSmsRequest(mtSmsReq);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (TapException e) {
            e.printStackTrace();
        }
    }

    private MtSmsReq getMtSmsReq(MoSmsReq moSmsReq) {
        final MtSmsReq mtSmsReq = new MtSmsReq();
        mtSmsReq.setApplicationId(appId);
        mtSmsReq.setPassword(password);
        mtSmsReq.setDestinationAddresses(Lists.newArrayList(moSmsReq.getSourceAddress()));
        mtSmsReq.setVersion(moSmsReq.getVersion());
        mtSmsReq.setEncoding(moSmsReq.getEncoding());
        mtSmsReq.setMessage(welcomMsg);
        return mtSmsReq;
    }
}