package in.co.indusnet.exception;


public class RegisterException extends RuntimeException{
        

	private static final long serialVersionUID = 1L;
	
	private int statusCode;
	
	public RegisterException(String message , int errorCode) {
		super(message);
		this.statusCode=errorCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int errorCode) {
		this.statusCode = errorCode;
	}
}
