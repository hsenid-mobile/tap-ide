package hms.ctap.simulator;


import hms.ctap.simulator.ui.SmsUiFactory;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Path("sms")
public class SmsNcsRestService {
    private static final Logger LOGGER = Logger.getLogger(SmsNcsRestService.class.getName());


    @POST
    @Path("/send")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendService(SmsMtRequestMessage smsMtRequestMessage) {


        LOGGER.info("Received message :- " + smsMtRequestMessage);

        List<DestinationResponse> destinationResponses = new ArrayList<DestinationResponse>();
        for (String address : smsMtRequestMessage.getDestinationAddresses()) {
            DestinationResponse destinationResponse = new DestinationResponse();
            destinationResponse.setAddress(address);
            destinationResponse.setStatusCode("S1000");
            destinationResponse.setStatusDetail("Success");
            destinationResponse.setRequestId(String.valueOf(System.currentTimeMillis()));

            destinationResponses.add(destinationResponse);
        }

        SmsMtResponse response = new SmsMtResponse("0.1", String.valueOf(System.currentTimeMillis()), "S1000", "Success", destinationResponses);
        setMtMessageToUi(smsMtRequestMessage);


        return Response.status(200).entity(response).build();
    }

    private void setMtMessageToUi(SmsMtRequestMessage message) {
        List<NotifyUI> notifyUis = SimulatorServer.getNotifyUis();
        for(NotifyUI notifyUI : notifyUis){
            notifyUI.notify(message.getMessage());
        }
    }


}
