package in.co.indusnet.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import in.co.indusnet.project.service.ProjectService;
import in.co.indusnet.response.Response;

@RestController
@RequestMapping("/indusnet")
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class ProjectController {
	
	@Autowired
	private ProjectService service;
	
	@PostMapping("/createProject")
	public ResponseEntity<Response> create(@Valid @RequestBody ProjectDto dto){
		 Response response=  service.createProject(dto);
		return new ResponseEntity<Response>(response , HttpStatus.OK);
	}

	@GetMapping("/getAllProjects")
	public List<ProjectModel> getProjects(){
		List<ProjectModel> model = service.getProject();
		return model;
	}
	
	@GetMapping("/getOneProjects")
	public ProjectModel getOneProjects(@RequestParam int pid){
		ProjectModel model = service.findProject(pid);
		return model;
	}
	
	@PutMapping("/assignProject")
	public ResponseEntity<Response> assign(@RequestParam int pid ,@RequestParam int eid){
		Response response = service.assignProject(pid, eid);
		return new ResponseEntity<Response>(response ,HttpStatus.OK);
		
	}
	
	@GetMapping("/getProject")
	public List<ProjectModel> getUserProjects(@RequestParam String mail){
		List<ProjectModel> projects = service.findUserProject(mail);
		return projects;
	}
	
	@PutMapping("/updateProj/{id}")
	public ResponseEntity<Response> updateProject(@Valid @RequestBody ProjectDto dto, @PathVariable int id) {
		Response response = service.update(dto, id);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}
	
	@DeleteMapping("/deleteProj/{id}")
	public ResponseEntity<Response> deleteProject(@PathVariable int id) {
		Response response = service.delete(id);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}
	
	@DeleteMapping("/removeProj")
	public ResponseEntity<Response> removeProject(@RequestParam int pid ,@RequestParam int eid) {
		Response response = service.remove(pid , eid);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}
}
