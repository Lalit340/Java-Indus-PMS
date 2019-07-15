package in.co.indusnet.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenGenerateException extends RuntimeException {

	private int statusCode;

	public TokenGenerateException(String message, int errorCode) {
		super(message);
		this.statusCode = errorCode;
	}
}
