package hms.ctap.simulator;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Path("ussd")
public class UssdNcsRestService {
    private static final Logger LOGGER = Logger.getLogger(UssdNcsRestService.class.getName());


    @POST
    @Path("send")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendService(UssdMtRequestMessage ussdMtRequestMessage) {


        LOGGER.info("Received message :- " + ussdMtRequestMessage);

        List<DestinationResponse> destinationResponses = new ArrayList<DestinationResponse>();
        String address = ussdMtRequestMessage.getDestinationAddress();
            DestinationResponse destinationResponse = new DestinationResponse();
            destinationResponse.setAddress(address);
            destinationResponse.setStatusCode("S1000");
            destinationResponse.setStatusDetail("Success");
            destinationResponse.setRequestId(String.valueOf(System.currentTimeMillis()));
            LOGGER.info(">>"+destinationResponse.toString());
            LOGGER.info("Ussd request>>"+ussdMtRequestMessage.toString());

            destinationResponses.add(destinationResponse);

        SmsMtResponse response = new SmsMtResponse("0.1", String.valueOf(System.currentTimeMillis()), "S1000", "Success", destinationResponses);
        setMtMessageToUi(ussdMtRequestMessage);


        return Response.status(200).entity(response).build();
    }

    private void setMtMessageToUi(UssdMtRequestMessage message) {
        List<NotifyUI> notifyUis = SimulatorServer.getNotifyUis();
        for(NotifyUI notifyUI : notifyUis){
            notifyUI.notify(message);
        }
    }


}
