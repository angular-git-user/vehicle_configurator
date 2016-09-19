package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.dto.UserDto;
import app.entityClasses.User;
import app.service.EmailService;
import app.service.UserService;

@RestController
@RequestMapping("")
public class UserController {
	
	@Autowired
	UserService service;
	
	//TODO
	@RequestMapping(method = RequestMethod.POST,value = "/register")
	public ResponseEntity<User> createNewUser(@RequestBody UserDto user){
		if(user != null){
			Integer id = service.createUser(user);
			if(id != null){
				return new ResponseEntity<User>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<User>(HttpStatus.CONFLICT);
	}
	
	//TODO
	@RequestMapping(method = RequestMethod.POST, value="/login")
	public ResponseEntity<String> login(@RequestBody UserDto user){
		String response = null;
		if(user != null){
			response = service.login(user.getUserId(), user.getPassword());
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
		return new ResponseEntity<String>(response, HttpStatus.UNAUTHORIZED);		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/users/{userId}/confirmation/{verificationCode}")
	public ResponseEntity<Boolean> confirmVerificationCode(@RequestParam String userId, @RequestParam int verificationCode){
		Boolean isVerified = service.verifyUser(userId, verificationCode);
		if(isVerified == null)
			return new ResponseEntity<Boolean>(HttpStatus.UNAUTHORIZED);	
		return new ResponseEntity<Boolean>(isVerified, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/users/{userId}/regenerate-verification-code")
	public ResponseEntity<Integer> regenerateVerificationCode(@RequestParam String userId){
		Integer code = null;
		if(userId != null){
			code = service.regenrateVerificationCode(userId);
		}
		return new ResponseEntity<Integer>(code, HttpStatus.OK);
	}
}
