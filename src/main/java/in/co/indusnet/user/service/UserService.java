package in.co.indusnet.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.co.indusnet.response.Response;
import in.co.indusnet.user.dto.UserLoginDto;
import in.co.indusnet.user.dto.UserPassword;
import in.co.indusnet.user.dto.UserRegisterDto;
import in.co.indusnet.user.model.UserModel;

@Service
public interface UserService {
	
	public Response saveData(UserRegisterDto user);
	
	public Response loginUser(UserLoginDto log);
	
	public Response resetPassword(UserPassword password ,String token);
	
	public List<UserModel> findAll();
	
}
