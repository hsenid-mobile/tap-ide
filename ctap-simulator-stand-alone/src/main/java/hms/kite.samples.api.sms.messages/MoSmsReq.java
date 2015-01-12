/*
*   (C) Copyright 1996-${year} hSenid Software International (Pvt) Limited.
*   All Rights Reserved.
*
*   These materials are unpublished, proprietary, confidential source code of
*   hSenid Software International (Pvt) Limited and constitute a TRADE SECRET
*   of hSenid Software International (Pvt) Limited.
*
*   hSenid Software International (Pvt) Limited retains all title to and intellectual
*   property rights in these materials.
*
*/

package hms.kite.samples.api.sms.messages;

public class MoSmsReq {

    private String version;
    private String applicationId;
    private String sourceAddress;
    private String message;
    private String requestId;
    private String encoding;

    private String deliveryStatusRequest;

    /**
     * Get the message received.
     * @return
     */
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Get the sender address.
     * @return
     */
    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    /**
     * Get the application Id.
     * @return
     */
    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    /**
     * Get the encoding type
     * @return
     */
    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * Get message id.
     * @return
     */
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * Get the version.
     * @return
     */
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDeliveryStatusRequest() {
        return deliveryStatusRequest;
    }

    public void setDeliveryStatusRequest(String deliveryStatusRequest) {
        this.deliveryStatusRequest = deliveryStatusRequest;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("MoSmsReq{")
                .append("applicationId='").append(applicationId).append('\'')
                .append(", version='").append(version).append('\'')
                .append(", sourceAddress='").append(sourceAddress).append('\'')
                .append(", message='").append(message).append('\'')
                .append(", requestId='").append(requestId).append('\'')
                .append(", encoding='").append(encoding).append('\'')
                .append(", deliveryStatusRequest='").append(deliveryStatusRequest).append('\'')
                .append('}').toString();
    }
}
