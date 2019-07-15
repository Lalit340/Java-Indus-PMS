package in.co.indusnet.task.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.indusnet.exception.InternalException;
import in.co.indusnet.project.dto.ProjectDto;
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
public class TaskServiceImplementation implements ProjectService {

	@Autowired
	private TaskRepository repository;

	@Autowired
	private ProjectRepository projRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public Response asignTask(TaskDto dto) {

		TaskModel model = modelMapper.map(dto, TaskModel.class);
		TaskModel responseModel = repository.save(model);
		if (responseModel == null) {
			throw new InternalException("Task has been not created because of Internal Problem", 500);
		}

		Response response = ResponseHelper.responseSender("Succefully Task created", 200);
		return response;
	}

	@Override
	public List<TaskModel> getData() {
		List<TaskModel> model = repository.findAll();
		if (model == null) {
			throw new InternalException("No Tasks are present ", 400);
		}
		return model;
	}

	@Override
	public Response delete(int id) {
		repository.deleteById(id);
		Response response = ResponseHelper.responseSender("Succefully Task Delete", 200);
		return response;
	}

	@Override
	public Response update(TaskDto dto, int id) {
		Optional<TaskModel> model = repository.findById(id);
		if (!model.isPresent()) {
			throw new InternalException("Task data Not Updated ", 401);
		}
		model.get().setDescription(dto.getDescription());
		model.get().setName(dto.getName());
		TaskModel responseModel = repository.save(model.get());
		if (responseModel == null) {
			throw new InternalException("Task data Not Updated because Of Some Internal problem", 402);
		}
		Response response = ResponseHelper.responseSender("Succefully Task Updated", 200);
		return response;
	}

	@Override
	public Response add(int pid, int tid) {
		Optional<TaskModel> taskModel = repository.findById(tid);
		if (!taskModel.isPresent()) {
			throw new InternalException("Task data Not Updated ", 401);
		}
		
		Optional<ProjectModel> projModel = projRepository.findById(pid);
		if (!projModel.isPresent()) {
			throw new InternalException("Task data Not Updated ", 401);
		}
	
		taskModel.get().setProjectModel(projModel.get());
		TaskModel responseModel = repository.save(taskModel.get());
		
		if (responseModel == null) {
			throw new InternalException("Task data Not Updated because Of Some Internal problem", 402);
		}
		Response response = ResponseHelper.responseSender("Succefully Task Updated", 200);
		return response;
	}


}
