package in.co.indusnet.project.controller;

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

@RestController
@RequestMapping("/Projects")
public class ProjectController {

	@Autowired
	private ProjectServiceImplementation service;

	@PostMapping("/create")
	public ResponseEntity<Response> create(@Valid @RequestBody ProjectDto dto) {
		Response response = service.asignProj(dto);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@GetMapping("/getallprojs")
	public List<ProjectModel> getProj() {
		List<ProjectModel> data = service.getData();
		return data;
	}

	@DeleteMapping("/deleteProj/{id}")
	public ResponseEntity<Response> deleteProject(@PathVariable int id) {
		Response response = service.delete(id);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@PutMapping("/updateProj/{id}")
	public ResponseEntity<Response> updateProject(@Valid @RequestBody ProjectDto dto, @PathVariable int id) {
		Response response = service.update(dto, id);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}
	@PutMapping("/asignProj")
	public ResponseEntity<Response> addProject( @RequestParam int pid , @RequestParam int eid) {
		Response response = service.add(pid, eid);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}
}
