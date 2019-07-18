package in.co.indusnet.task.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.co.indusnet.project.dto.ProjectDto;
import in.co.indusnet.project.model.ProjectModel;
import in.co.indusnet.project.service.ProjectServiceImplementation;
import in.co.indusnet.response.Response;
import in.co.indusnet.task.dto.TaskDto;
import in.co.indusnet.task.model.TaskModel;
import in.co.indusnet.task.service.TaskServiceImplementation;

@RestController
@RequestMapping("/indusnet")
public class TaskController {

	@Autowired
	private TaskServiceImplementation service;

	@PostMapping("/createtask")
	public ResponseEntity<Response> create(@Valid @RequestBody TaskDto dto) {
		Response response = service.asignTask(dto);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@GetMapping("/getalltasks")
	public List<TaskModel> getTask() {
		List<TaskModel> data = service.getData();
		return data;
	}

	@DeleteMapping("/deletetask/{id}")
	public ResponseEntity<Response> deleteTask(@PathVariable int id) {
		Response response = service.delete(id);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@PutMapping("/updatetask/{id}")
	public ResponseEntity<Response> updateTask(@Valid @RequestBody TaskDto dto, @PathVariable int id) {
		Response response = service.update(dto, id);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}
	@PutMapping("/asigntask")
	public ResponseEntity<Response> addTask( @RequestParam int pid , @RequestParam int tid) {
		Response response = service.add(pid, tid);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}
}
