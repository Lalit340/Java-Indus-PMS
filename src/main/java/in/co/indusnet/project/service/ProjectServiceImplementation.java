package in.co.indusnet.project.service;

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
import in.co.indusnet.user.model.UserModel;
import in.co.indusnet.user.repository.UserRepository;
import in.co.indusnet.utility.ResponseHelper;

@Service("projService")
public class ProjectServiceImplementation implements ProjectService {

	@Autowired
	private ProjectRepository repository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public Response asignProj(ProjectDto dto) {

		ProjectModel model = modelMapper.map(dto, ProjectModel.class);
		ProjectModel responseModel = repository.save(model);
		if (responseModel == null) {
			throw new InternalException("Project has been not created because of Internal Problem", 500);
		}

		Response response = ResponseHelper.responseSender("Succefully project created", 200);
		return response;
	}

	@Override
	public List<ProjectModel> getData() {
		List<ProjectModel> model = repository.findAll();
		if (model == null) {
			throw new InternalException("No Projects are present ", 400);
		}
		return model;
	}

	@Override
	public Response delete(int id) {
		repository.deleteById(id);
		Response response = ResponseHelper.responseSender("Succefully project Delete", 200);
		return response;
	}

	@Override
	public Response update(ProjectDto dto, int id) {
		Optional<ProjectModel> model = repository.findById(id);
		if (!model.isPresent()) {
			throw new InternalException("Project data Not Updated ", 401);
		}
		model.get().setDescription(dto.getDescription());
		model.get().setName(dto.getName());
		ProjectModel responseModel = repository.save(model.get());
		if (responseModel == null) {
			throw new InternalException("Project data Not Updated because Of Some Internal problem", 402);
		}
		Response response = ResponseHelper.responseSender("Succefully project Updated", 200);
		return response;
	}

	@Override
	public Response add(int pid, int eid) {
		Optional<ProjectModel> projectModel = repository.findById(pid);
		if (!projectModel.isPresent()) {
			throw new InternalException("Project data Not Updated ", 401);
		}
		
		Optional<UserModel> userModel = userRepository.findById(eid);
		if (!userModel.isPresent()) {
			throw new InternalException("Project data Not Updated ", 401);
		}
		
		projectModel.get().getUserModel().add(userModel.get());
		ProjectModel responseModel = repository.save(projectModel.get());
		
		if (responseModel == null) {
			throw new InternalException("Project data Not Updated because Of Some Internal problem", 402);
		}
		Response response = ResponseHelper.responseSender("Succefully project Updated", 200);
		return response;
	}
}
