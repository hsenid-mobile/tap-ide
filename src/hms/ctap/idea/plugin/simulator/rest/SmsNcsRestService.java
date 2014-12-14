package hms.ctap.idea.plugin.simulator.rest;

import hms.ctap.idea.plugin.simulator.SmsMtRequestMessage;
import hms.ctap.idea.plugin.simulator.SmsMtResponse;
import hms.ctap.idea.plugin.ui.CtapSimulatorWindowFactory;
import hms.ctap.idea.plugin.ui.SmsUiFactory;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("sms")
public class SmsNcsRestService {
	private static final Logger LOGGER = Logger.getLogger(SmsNcsRestService.class.getName());

	@POST
	@Path("/send")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendService(SmsMtRequestMessage smsMtRequestMessage) {

		LOGGER.info("Received message :- " + smsMtRequestMessage);

		SmsMtResponse response = new SmsMtResponse("0.1", String.valueOf(System.currentTimeMillis()), "S1000", "Success",
				"tel:947712345678");

		setMtMessageToUi(smsMtRequestMessage);

		return Response.status(200).entity(response).build();
	}

	private void setMtMessageToUi(SmsMtRequestMessage message) {
		Object uiObject = CtapSimulatorWindowFactory.getInitializedUIObject(CtapSimulatorWindowFactory.SMS_UI_FACTORY);
		if(uiObject != null){
			if(uiObject instanceof SmsUiFactory){
				SmsUiFactory smsUiFactory = (SmsUiFactory) uiObject;
				smsUiFactory.createMsgReceivedUI(message.getMessage());
			}
		}
	}


}
