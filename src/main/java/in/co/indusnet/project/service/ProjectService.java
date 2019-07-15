package in.co.indusnet.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.co.indusnet.project.dto.ProjectDto;
import in.co.indusnet.project.model.ProjectModel;
import in.co.indusnet.response.Response;

@Service
public interface ProjectService {
	
	public Response asignProj(ProjectDto dto);

	public List<ProjectModel> getData( );
	
	public Response delete(int id);
	
	public Response update(ProjectDto dto ,int id);
	
	public Response add(int pid ,int eid);

	public Response remove(int pid, int eid);

	
}
