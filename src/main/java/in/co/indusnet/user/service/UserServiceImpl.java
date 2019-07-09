package in.co.indusnet.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.indusnet.user.model.UserModal;
import in.co.indusnet.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository repository;
	
	public void saveData(UserModal user) {
		repository.save(user);
	}
}
