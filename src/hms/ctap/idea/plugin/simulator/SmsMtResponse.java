package hms.ctap.idea.plugin.simulator;

public class SmsMtResponse {

	private String version;
	private String requestId;
	private String statusCode;
	private String statusDetail;
	private String destinationResponses;

	public SmsMtResponse(String version, String requestId, String statusCode, String statusDetail,
			String destinationResponses) {
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

	public String getDestinationResponses() {
		return destinationResponses;
	}

	public void setDestinationResponses(String destinationResponses) {
		this.destinationResponses = destinationResponses;
	}

}
