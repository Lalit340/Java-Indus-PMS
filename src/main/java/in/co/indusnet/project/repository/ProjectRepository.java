package in.co.indusnet.project.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.co.indusnet.project.model.ProjectModel;
import in.co.indusnet.user.model.UserModel;

public interface ProjectRepository extends JpaRepository<ProjectModel, Integer> {
   

	//public Optional<UserModel> findByMail(String mail);

}
