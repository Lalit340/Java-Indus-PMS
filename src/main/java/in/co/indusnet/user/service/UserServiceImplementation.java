package in.co.indusnet.user.service;

import java.util.ArrayList;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.co.indusnet.exception.RegisterException;
import in.co.indusnet.user.dto.UserRegisterDto;
import in.co.indusnet.user.model.UserModel;
import in.co.indusnet.user.repository.UserRepository;

@Service("userService")
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder password;

	@Autowired
	private ModelMapper modelMapper;

	public void saveData(UserRegisterDto user) {
		user.setPassword(password.encode(user.getPassword()));
//		ArrayList<UserModel>  arr= (ArrayList<UserModel>) repository.findAll();
//		UserModel email =arr.stream().filter(mail -> mail.getMail().equals(user.getMail())).findAny().get();
//        if(email.getMail() == user.getMail()) {
//        	throw new RegisterException("already this user is present", -10);
//        }
		UserModel model = modelMapper.map(user, UserModel.class);
		repository.save(model);
	}
}
