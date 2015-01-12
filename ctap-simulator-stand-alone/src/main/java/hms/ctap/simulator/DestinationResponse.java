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
package hms.ctap.simulator;

/**
 * This class contains the status information of a sent mtSmsReq to a specific address. When MtRequest is sent
 * via SmsRequestSender, MtResponse contains a list of DestinationResponse .
 */
public class DestinationResponse {

    private String address;
    private String timeStamp;
    private String requestId;
    private String statusCode;
    private String statusDetail;

    /**
     * Get the statue code
     *
     * @return
     */
    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Get the status code description.
     *
     * @return
     */
    public String getStatusDetail() {
        return statusDetail;
    }

    public void setStatusDetail(String statusDetail) {
        this.statusDetail = statusDetail;
    }

    /**
     * Get address of message recipient.
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get the message id.
     *
     * @return
     */
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DestinationResponse");
        sb.append("{ statusCode='").append(statusCode).append('\'');
        sb.append(", statusDetail='").append(statusDetail).append('\'');
        sb.append(", timeStamp='").append(timeStamp).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", requestId='").append(requestId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
