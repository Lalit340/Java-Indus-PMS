package in.co.indusnet.user.service;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.co.indusnet.exception.PasswordException;
import in.co.indusnet.exception.RegisterException;
import in.co.indusnet.response.Response;
import in.co.indusnet.user.dto.UserLoginDto;
import in.co.indusnet.user.dto.UserPassword;
import in.co.indusnet.user.dto.UserRegisterDto;
import in.co.indusnet.user.model.UserModel;
import in.co.indusnet.user.repository.UserRepository;
import in.co.indusnet.utility.ResponseHelper;

@Service("userService")
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder password;

	@Autowired
	private ModelMapper modelMapper;

	public Response saveData(UserRegisterDto user) {
		Response response = null;
		user.setPassword(password.encode(user.getPassword()));
		Optional<UserModel> validate = repository.findByMail(user.getMail());
		if (validate.isPresent()) {
			throw new RegisterException("already this user is present", 502);
		}

		UserModel model = modelMapper.map(user, UserModel.class);
		UserModel dataResponse = repository.save(model);
		if (dataResponse == null) {
			throw new RegisterException("data is not saved in database", 501);
		}
		response = ResponseHelper.responseSender("Registration Successful", 200);

		return response;
	}// saveData

	@Override
	public Response loginUser(UserLoginDto log) {

		Optional<UserModel> validate = null;
		Response response = null;

		validate = repository.findByMail(log.getMail());

		if (validate.isPresent()) {
			if (password.matches(log.getPassword(), validate.get().getPassword())) {
				if (validate.get().getDesg().equals("Developer")) {
					response = ResponseHelper.responseSender("User Login Successful ", 201);
					return response;
				} else if (validate.get().getDesg().equals("Team Leader")) {
					response = ResponseHelper.responseSender("User Login Successful ", 202);
					return response;
				} else if (validate.get().getDesg().equals("Project Manager")) {
					response = ResponseHelper.responseSender("User Login Successful ", 203);
					return response;
				} else if (validate.get().getDesg().equals("Designer")) {
					response = ResponseHelper.responseSender("User Login Successful ", 204);
					return response;
				} else {
					throw new in.co.indusnet.exception.LoginException("designation not matched", 400);
				}

			} else
				throw new in.co.indusnet.exception.LoginException("Email is not valid", 401);
		} else {
			throw new in.co.indusnet.exception.LoginException("Email is not exist", 402);
		}

	}// loginUser

	@Override
	public Response resetPassword(UserPassword password, String token) {
		Optional<UserModel> validate = null;
		Response response = null;
		validate = repository.findByMail(token);

		if (password.getPassword().equals(password.getConformPassword())) {
			validate.get().setPassword(this.password.encode(password.getPassword()));
			repository.save(validate.get());
			response = ResponseHelper.responseSender(" password changes Successfully ", 205);
			return response;
		} else {
			throw new PasswordException("two password are not matched", 410);
		}
	}// resetPassword

	@Override
	public List<UserModel> findAll() {
		List<UserModel> model = null;
		model = repository.findAll();
		if(model != null) {
			return model;
		}else
			throw new RegisterException("Data not found", 412);
		
	}//findAll

}// class
