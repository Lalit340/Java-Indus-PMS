package in.co.indusnet.task.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import in.co.indusnet.task.model.TaskModel;

public interface TaskRepository extends JpaRepository<TaskModel, Integer> {
   

	//public Optional<UserModel> findByMail(String mail);

}
