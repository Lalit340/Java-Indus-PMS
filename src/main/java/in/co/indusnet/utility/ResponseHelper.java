package in.co.indusnet.utility;

import in.co.indusnet.response.Response;
import in.co.indusnet.response.ResponseWithToken;

public class ResponseHelper {
	
	public static Response responseSender(String message , int statusCode) {
		Response response = new Response();
		response.setStatusCode(statusCode);
		response.setStatusMessage(message);
		return response;
	}
	
	public static ResponseWithToken responseTokenSender(String message , int statusCode,String token ,String username) {
		ResponseWithToken response = new ResponseWithToken();
		response.setStatusCode(statusCode);
		response.setMessage(message);
		response.setToken(token);
		response.setUsername(username);
		return response;
	}
	
	public static ResponseWithToken response(String message , int statusCode) {
		ResponseWithToken response = new ResponseWithToken();
		response.setStatusCode(statusCode);
		response.setMessage(message);
		return response;
	}

}
