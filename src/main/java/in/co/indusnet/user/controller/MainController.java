package in.co.indusnet.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.co.indusnet.user.model.UserModal;
import in.co.indusnet.user.service.UserService;

@RestController
@RequestMapping("/indusnet")
public class MainController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/savedata")
	public void data(@RequestBody UserModal usr) {
		service.saveData(usr);
	}

}
