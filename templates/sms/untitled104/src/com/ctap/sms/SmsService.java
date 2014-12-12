package ${IJ_BASE_PACKAGE}.sms;

import com.google.common.collect.Lists;

import hms.kite.samples.api.SdpException;
import hms.kite.samples.api.caas.ChargingRequestSender;
import hms.kite.samples.api.caas.messages.ChargingRequestResponse;
import hms.kite.samples.api.caas.messages.DirectDebitRequest;
import hms.kite.samples.api.sms.MoSmsListener;
import hms.kite.samples.api.sms.SmsRequestSender;
import hms.kite.samples.api.sms.messages.MoSmsReq;
import hms.kite.samples.api.sms.messages.MtSmsReq;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.logging.Logger;

import static ${IJ_BASE_PACKAGE}.util.ConfigUtil.*;

public class SmsService implements MoSmsListener{

    private static final String welcomMsg = getDisplayMessage("welcome.message").or("Welcome.");

    private static final String appId = getApplicationConfig("app.id");
    private static final String password = getApplicationConfig("app.password");

    private static final Logger logger = Logger.getLogger("logger");

    @Override
    public void init() {
    }

    @Override
    public void onReceivedSms(MoSmsReq moSmsReq) {
        //
        try {
            SmsRequestSender smsRequestSender = new SmsRequestSender(new URL(getApplicationConfig("nbl.sms.endpoint")));
            final MtSmsReq mtSmsReq = getMtSmsReq(moSmsReq);
            smsRequestSender.sendSmsRequest(mtSmsReq);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SdpException e) {
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