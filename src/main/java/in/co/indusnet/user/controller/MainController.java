package in.co.indusnet.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.co.indusnet.response.Response;
import in.co.indusnet.user.dto.UserLoginDto;
import in.co.indusnet.user.dto.UserPassword;
import in.co.indusnet.user.dto.UserRegisterDto;
import in.co.indusnet.user.model.UserModel;
import in.co.indusnet.user.service.UserService;

@RestController
@RequestMapping("/indusnet")
public class MainController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/register")
	public ResponseEntity<Response> register(@Valid	@RequestBody UserRegisterDto usr) {
		Response response = service.saveData(usr);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Response> login(@Valid @RequestBody UserLoginDto log ) {
		Response response= service.loginUser(log);
		return new ResponseEntity<Response>(response , HttpStatus.OK);
	}
	
	@PutMapping("/resetpassword/{token}")
	public ResponseEntity<Response> reset(@Valid @RequestBody UserPassword password ,String token) {
		 Response response = service.resetPassword(password ,token);
         return new ResponseEntity<Response>(response ,HttpStatus.OK);
	}
	
	@GetMapping("/usersinfo")
	public List<UserModel> find( ) {
		List<UserModel> model= service.findAll();
		return model;
	}
	
}
