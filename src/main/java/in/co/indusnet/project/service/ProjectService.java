package in.co.indusnet.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.co.indusnet.project.dto.ProjectDto;
import in.co.indusnet.project.model.ProjectModel;
import in.co.indusnet.response.Response;

@Service
public interface ProjectService {

	public Response createProject(ProjectDto dto);
	
	public List<ProjectModel> getProject();

	public Response assignProject(int pid ,int eid);

	public List<ProjectModel> findUserProject(String mail);
	
	public Response update(ProjectDto dto, int id);
	
	public Response delete(int id);
	
	public Response remove(int pid, int eid);

	public ProjectModel findProject(int pid);

}
