package in.co.indusnet.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PasswordException extends RuntimeException {

	private int statusCode;

	public PasswordException(String statusMassage, int statusCode) {
		super(statusMassage);
		this.statusCode = statusCode;
	}
}
