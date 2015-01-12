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

import hms.kite.samples.api.sms.DestinationResponse;

import java.util.List;

public class MtSmsResp {

    private String version;
    private String requestId;
    private String statusCode;
    private String statusDetail;
    private List<DestinationResponse> destinationResponses;

    /**
     * @return Status of sent sms.
     */
    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return Get Status Description
     */
    public String getStatusDetail() {
        return statusDetail;
    }

    public void setStatusDetail(String statusDetail) {
        this.statusDetail = statusDetail;
    }

    /**
     * Get message Id
     * @return
     */
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * Get Version of the message
     * @return
     */
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Get Destination Responses. Destination Response contains the status of message for each recipient.
     * @return
     */
    public List<DestinationResponse> getDestinationResponses() {
        return destinationResponses;
    }

    public void setDestinationResponses(List<DestinationResponse> destinationResponses) {
        this.destinationResponses = destinationResponses;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("MtSmsResp{")
                .append("destinationResponses=").append(destinationResponses)
                .append(", version='").append(version).append('\'')
                .append(", requestId='").append(requestId).append('\'')
                .append(", statusCode='").append(statusCode).append('\'')
                .append(", statusDetail='").append(statusDetail).append('\'')
                .append('}').toString();
    }
}
