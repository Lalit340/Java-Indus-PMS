package in.co.indusnet.task.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.indusnet.exception.InternalException;
import in.co.indusnet.project.model.ProjectModel;
import in.co.indusnet.project.repository.ProjectRepository;
import in.co.indusnet.response.Response;
import in.co.indusnet.task.dto.TaskDto;
import in.co.indusnet.task.model.TaskModel;
import in.co.indusnet.task.repository.TaskRepository;
import in.co.indusnet.user.model.UserModel;
import in.co.indusnet.user.repository.UserRepository;
import in.co.indusnet.utility.ResponseHelper;

@Service("taskService")
public class TaskServiceImplementation implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProjectRepository projRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Response createTask(TaskDto dto) {

		TaskModel model = modelMapper.map(dto, TaskModel.class);
		TaskModel responseModel = taskRepository.save(model);
		if (responseModel == null) {
			throw new InternalException("Task has been not created because of Internal Problem", 500);
		}

		Response response = ResponseHelper.responseSender("Succefully Task created", 200);
		return response;
	}

	@Override
	public List<TaskModel> getData() {
		List<TaskModel> model = taskRepository.findAll();
		return model;
	}

	@Override
	public Response asignTask(int pid, int tid) {
		Optional<ProjectModel> projectModel = projRepository.findById(pid);
		if (!projectModel.isPresent())
			throw new InternalException("No project  is present in the pid ", 400);
		Optional<TaskModel> taskModel = taskRepository.findById(tid);
		if (!taskModel.isPresent())
			throw new InternalException("No Task  is present in the tid ", 400);
		if (!projectModel.get().getTaskModel().contains(taskModel.get())
				&& !taskModel.get().getProject().contains(projectModel.get())) {
			projectModel.get().getTaskModel().add(taskModel.get());
			taskModel.get().getProject().add(projectModel.get());
			projRepository.save(projectModel.get());
			taskRepository.save(taskModel.get());
			Response response = ResponseHelper.responseSender("Succefully Task asign to project ", 200);
			return response;
		} else
			throw new InternalException("Task is already present ", 400);
	}
	

	@Override
	public Response asignTaskUser(int eid, int tid) {
		Optional<UserModel> userModel = userRepository.findById(eid);
		if (!userModel.isPresent())
			throw new InternalException("No user  is present in the eid ", 400);
		Optional<TaskModel> taskModel = taskRepository.findById(tid);
		if (!taskModel.isPresent())
			throw new InternalException("No Task  is present in the tid ", 400);
		if (!userModel.get().getTask().contains(taskModel.get())
				&& !taskModel.get().getUser().contains(userModel.get())) {
			userModel.get().getTask().add(taskModel.get());
			taskModel.get().getUser().add(userModel.get());
			userRepository.save(userModel.get());
			taskRepository.save(taskModel.get());
			Response response = ResponseHelper.responseSender("Succefully Task asign to user ", 200);
			return response;
		} else
			throw new InternalException("Task is already present ", 400);
	}

	@Override
	public List<TaskModel> getListTask(int pid) {
		Optional<ProjectModel> projectModel = projRepository.findById(pid);
		if (projectModel.isPresent()) {
			List<TaskModel> taskModel = projectModel.get().getTaskModel();
			System.out.println(" tasks :" + taskModel);
			return taskModel;
		} else
			throw new InternalException("In this pid  project is not present ", 400);
	}

	@Override
	public Response changeStatus(int tid) {
		Optional<TaskModel> taskModel = taskRepository.findById(tid);
		if (taskModel.isPresent()) {
			if (taskModel.get().isStatus()) {
				taskModel.get().setStatus(false);
				taskRepository.save(taskModel.get());
				System.out.println("status " + taskModel.get().isStatus());
				Response response = ResponseHelper.responseSender("Succefully Change the status ", 200);
				return response;

			} else {
				taskModel.get().setStatus(true);
				taskRepository.save(taskModel.get());
				System.out.println("status " + taskModel.get().isStatus());
				Response response = ResponseHelper.responseSender("Succefully hange the status ", 200);
				return response;

			}
		} else
			throw new InternalException("In this tid  Task is not present ", 400);
	}

	
	@Override
	public Response delete(int id) {
		taskRepository.deleteById(id);
		Response response = ResponseHelper.responseSender("Succefully Task Delete", 200);
		return response;
	}
	
	@Override
	public Response remove(int pid, int tid) {
		Optional<ProjectModel> model = projRepository.findById(pid);
		if (!model.isPresent()) {
			throw new InternalException("No Projects are present ", 401);
		}
		Optional<TaskModel> taskModel = taskRepository.findById(tid);
		if (!taskModel.isPresent()) {
			throw new InternalException("No UserData are present ", 402);
		}
		if (model.get().getTaskModel().remove(taskModel.get())) {
			if (taskModel.get().getProject().remove(model.get())) {
				taskRepository.save(taskModel.get());
				Response response = ResponseHelper.responseSender("Succefully user Deleted", 200);
				return response;
			} else {
				Response response = ResponseHelper.responseSender("user not Deleted from project ", 403);
				return response;
			}
		} else {
			Response response = ResponseHelper.responseSender("user not Deleted from project ", 403);
			return response;
		}
	}

	

	@Override
	public Response update(TaskDto dto, int id) {
		Optional<TaskModel> model = taskRepository.findById(id);
		if (!model.isPresent()) {
			throw new InternalException("Task data Not Updated ", 401);
		}
		model.get().setDescription(dto.getDescription());
		model.get().setName(dto.getName());
		TaskModel responseModel = taskRepository.save(model.get());
		if (responseModel == null) {
			throw new InternalException("Task data Not Updated because Of Some Internal problem", 402);
		}
		Response response = ResponseHelper.responseSender("Succefully Task Updated", 200);
		return response;
	}
	
	@Override
	public List<TaskModel> getTask(String mail) {
		Optional<UserModel> userModel = userRepository.findByMail(mail);
		if(userModel.isPresent()) {
			List<TaskModel> model = userModel.get().getTask();
			System.out.println(" task :"+model);
			return model;
		}else
			throw new InternalException("In this eid  user is not present ", 400);
	}

}
