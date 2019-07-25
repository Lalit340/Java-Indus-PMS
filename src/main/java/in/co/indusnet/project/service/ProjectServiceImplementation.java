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

@Service("/projectService")
public class ProjectServiceImplementation implements ProjectService {

	@Autowired
	private ProjectRepository projRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public Response createProject(ProjectDto dto) {
		ProjectModel model = mapper.map(dto, ProjectModel.class);
		ProjectModel responseModel = projRepository.save(model);
		Response response = null;
		if (responseModel == null) {
			throw new InternalException("project add is failed", 401);
		}
		response = ResponseHelper.responseSender("Project is Created Successfully", 200);
		return response;
	}

	@Override
	public List<ProjectModel> getProject() {
		List<ProjectModel> projects = projRepository.findAll();
		if (projects != null)
			return projects;
		else
			throw new InternalException("project data is not found", 401);
	}

	@Override
	public Response assignProject(int pid, int eid) {
		Response response = null;
		Optional<ProjectModel> projModel = projRepository.findById(pid);
		if (!projModel.isPresent()) {
			throw new InternalException("project data is not found", 401);
		}
		Optional<UserModel> userModel = userRepository.findById(eid);
		if (!userModel.isPresent()) {
			throw new InternalException("User data is not found", 401);
		}
		if (!projModel.get().getUserModel().contains(userModel.get())) {
			if (!userModel.get().getProjectModel().contains(projModel.get())) {
				projModel.get().getUserModel().add(userModel.get());
				projRepository.save(projModel.get());
				userModel.get().getProjectModel().add(projModel.get());
				userRepository.save(userModel.get());
				response = ResponseHelper.responseSender("Successfully asigned project", 200);
				return response;
			} else
				throw new InternalException("user is already have this project", 401);
		} else
			throw new InternalException("project already asigned to user", 401);
	}

	@Override
	public List<ProjectModel> findUserProject(String mail) {
		Optional<UserModel> userModel = userRepository.findByMail(mail);
		if (userModel.isPresent()) {
			List<ProjectModel> projModel = userModel.get().getProjectModel();
			System.out.println("hi ::" + projModel);
			return projModel;
		} else
			throw new InternalException("User Not Found", 401);

	}

	@Override
	public Response update(ProjectDto dto, int id) {
		Optional<ProjectModel> model = projRepository.findById(id);
		if (!model.isPresent()) {
			throw new InternalException("Project data Not Updated ", 401);
		}
		model.get().setDescription(dto.getDescription());
		model.get().setName(dto.getName());
		ProjectModel responseModel = projRepository.save(model.get());
		if (responseModel == null) {
			throw new InternalException("Project data Not Updated because Of Some Internal problem", 402);
		}
		Response response = ResponseHelper.responseSender("Succefully project Updated", 200);
		return response;
	}

	@Override
	public Response delete(int id) {
		projRepository.deleteById(id);
		Response response = ResponseHelper.responseSender("Succefully project Delete", 200);
		return response;
	}

	@Override
	public Response remove(int pid, int eid) {
		Optional<ProjectModel> model = projRepository.findById(pid);
		if (!model.isPresent()) {
			throw new InternalException("No Projects are present ", 401);
		}
		Optional<UserModel> userModel = userRepository.findById(eid);
		if (!userModel.isPresent()) {
			throw new InternalException("No UserData are present ", 402);
		}
		if (model.get().getUserModel().remove(userModel.get())) {
			if (userModel.get().getProjectModel().remove(model.get())) {
				projRepository.save(model.get());
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

}
