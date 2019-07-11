package in.co.indusnet.response;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Setter
@Getter
public class Response {

	private String statusMessage;
	
	private int statusCode;


}
