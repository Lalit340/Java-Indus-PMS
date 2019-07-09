package in.co.indusnet.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.co.indusnet.user.model.UserModal;

public interface UserRepository extends JpaRepository<UserModal, Integer> {

}
