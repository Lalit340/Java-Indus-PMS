package in.co.indusnet.response;

import org.springframework.stereotype.Component;

@Component
public class Response {

	private String errorMessage;
	
	private int errorCode;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
}
