package hms.ctap.simulator;

public class UssdMtRequestMessage {

    private String applicationId;
    private String password;
    private String version;
    private String message;
    private String sessionId;
    private String ussdOperation;
    private String destinationAddress;
    private String encoding;
    private String chargingAmount;

    public String getUssdOperation() {
        return ussdOperation;
    }

    public void setUssdOperation(String ussdOperation) {
        this.ussdOperation = ussdOperation;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getChargingAmount() {
        return chargingAmount;
    }

    public void setChargingAmount(String chargingAmount) {
        this.chargingAmount = chargingAmount;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("MtUssdReq{")
                .append("applicationId='").append(applicationId).append('\'')
                .append(", password='").append(password).append('\'')
                .append(", version='").append(version).append('\'')
                .append(", message='").append(message).append('\'')
                .append(", sessionId='").append(sessionId).append('\'')
                .append(", ussdOperation='").append(ussdOperation).append('\'')
                .append(", destinationAddress='").append(destinationAddress).append('\'')
                .append(", encoding='").append(encoding).append('\'')
                .append(", chargingAmount='").append(chargingAmount).append('\'')
                .append('}').toString();
    }
}