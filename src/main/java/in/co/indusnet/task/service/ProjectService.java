package in.co.indusnet.task.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.co.indusnet.project.dto.ProjectDto;
import in.co.indusnet.response.Response;
import in.co.indusnet.task.dto.TaskDto;
import in.co.indusnet.task.model.TaskModel;

@Service
public interface ProjectService {
	
	public Response asignTask(TaskDto dto);

	public List<TaskModel> getData( );
	
	public Response delete(int id);
	
	public Response update(TaskDto dto ,int id);
	
	public Response add(int pid ,int eid);

	
}
