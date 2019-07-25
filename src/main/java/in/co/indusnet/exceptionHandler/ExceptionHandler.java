package in.co.indusnet.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.co.indusnet.exception.InternalException;
import in.co.indusnet.exception.LoginException;
import in.co.indusnet.exception.PasswordException;
import in.co.indusnet.exception.RegisterException;
import in.co.indusnet.response.Response;
import in.co.indusnet.utility.ResponseHelper;

@RestControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = RegisterException.class)
	public ResponseEntity<Response> registrationResponse(RegisterException re){
		    Response response= ResponseHelper.responseSender(re.getMessage(), re.getStatusCode()) ;
		return new ResponseEntity<Response>(response ,HttpStatus.OK);
		
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = LoginException.class)
	public ResponseEntity<Response> loginResponse(LoginException re){
		    Response response= ResponseHelper.responseSender(re.getMessage(), re.getStatusCode()) ;
		return new ResponseEntity<Response>(response ,HttpStatus.OK);
		
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = PasswordException.class)
	public ResponseEntity<Response> passwordResponse( PasswordException re){
		    Response response= ResponseHelper.responseSender(re.getMessage(), re.getStatusCode()) ;
		return new ResponseEntity<Response>(response ,HttpStatus.OK);
		
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = InternalException.class)
	public ResponseEntity<Response> internalResponse( InternalException re){
		    Response response= ResponseHelper.responseSender(re.getMessage(), re.getStatusCode()) ;
		return new ResponseEntity<Response>(response ,HttpStatus.OK);
		
	}

}
