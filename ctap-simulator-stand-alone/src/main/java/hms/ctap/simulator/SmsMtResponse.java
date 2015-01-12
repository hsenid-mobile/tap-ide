package hms.ctap.simulator;

import java.util.List;

public class SmsMtResponse {

	private String version;
	private String requestId;
	private String statusCode;
	private String statusDetail;
	private List<DestinationResponse> destinationResponses;

	public SmsMtResponse(String version, String requestId, String statusCode, String statusDetail,
						 List<DestinationResponse> destinationResponses) {
		super();
		this.version = version;
		this.requestId = requestId;
		this.statusCode = statusCode;
		this.statusDetail = statusDetail;
		this.destinationResponses = destinationResponses;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDetail() {
		return statusDetail;
	}

	public void setStatusDetail(String statusDetail) {
		this.statusDetail = statusDetail;
	}

	public List<DestinationResponse> getDestinationResponses() {
		return destinationResponses;
	}

	public void setDestinationResponses(List<DestinationResponse> destinationResponses) {
		this.destinationResponses = destinationResponses;
	}

}
