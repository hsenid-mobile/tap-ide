package hms.ctap.idea.plugin.simulator;

import java.util.List;

public class SmsMtRequestMessage {

	private String version;
	private String applicationId;
	private String sourceAddress;
	private String message;
	private String requestId;
	private String encoding;

	private String password;
	private String binaryHeader;
	private String subscriberId;
	private String deliveryStatusRequest;
	private List<String> destinationAddresses;

	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getSourceAddress() {
		return sourceAddress;
	}

	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getBinaryHeader() {
		return binaryHeader;
	}

	public void setBinaryHeader(String binaryHeader) {
		this.binaryHeader = binaryHeader;
	}

	public String getDeliveryStatusRequest() {
		return deliveryStatusRequest;
	}

	public void setDeliveryStatusRequest(String deliveryStatusRequest) {
		this.deliveryStatusRequest = deliveryStatusRequest;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<String> getDestinationAddresses() {
		return destinationAddresses;
	}

	public void setDestinationAddresses(List<String> destinationAddresses) {
		this.destinationAddresses = destinationAddresses;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SmsRequestMessage [version=");
		builder.append(version);
		builder.append(", applicationId=");
		builder.append(applicationId);
		builder.append(", sourceAddress=");
		builder.append(sourceAddress);
		builder.append(", message=");
		builder.append(message);
		builder.append(", requestId=");
		builder.append(requestId);
		builder.append(", encoding=");
		builder.append(encoding);
		builder.append(", password=");
		builder.append(password);
		builder.append(", binaryHeader=");
		builder.append(binaryHeader);
		builder.append(", subscriberId=");
		builder.append(subscriberId);
		builder.append(", deliveryStatusRequest=");
		builder.append(deliveryStatusRequest);
		builder.append(", destinationAddresses=");
		builder.append(destinationAddresses);
		builder.append("]");
		return builder.toString();
	}

}
