package in.co.indusnet.task.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.co.indusnet.response.Response;
import in.co.indusnet.task.dto.TaskDto;
import in.co.indusnet.task.model.TaskModel;

@Service
public interface TaskService {

	public Response createTask(TaskDto dto);
	
	public List<TaskModel> getData();
	
	public Response asignTask(int pid, int tid);
	
	public Response asignTaskUser(int eid, int tid);
	
	public List<TaskModel> getListTask(int pid);
	
	public List<TaskModel> getTask(String mail);

	public Response changeStatus(int tid);
	
	public Response delete(int id);
	
	public Response remove(int pid, int tid);
	
	public Response update(TaskDto dto, int id);

}
