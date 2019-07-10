package in.co.indusnet.user.service;

import org.springframework.stereotype.Service;

import in.co.indusnet.user.dto.UserRegisterDto;

@Service
public interface UserService {
	
	public void saveData(UserRegisterDto user);
}
