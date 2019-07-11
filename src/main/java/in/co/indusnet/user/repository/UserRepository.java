package in.co.indusnet.user.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.co.indusnet.user.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
   

	public Optional<UserModel> findByMail(String mail);

}
