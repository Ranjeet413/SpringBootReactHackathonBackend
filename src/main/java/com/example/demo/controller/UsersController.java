package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.JWTRequest;
import com.example.demo.model.JWTResponse;
import com.example.demo.model.Users;
import com.example.demo.service.MyUserDetailService;
import com.example.demo.service.UsersService;
import com.example.demo.util.JWTUtility;

@RestController
@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:3000"})
public class UsersController {

	@Autowired
	UsersService UsersServ;
	
	@Autowired
	AuthenticationManager manager;
	
	@Autowired
	JWTUtility jwtUtitlity;
	
	@Autowired
	MyUserDetailService userDetailService;
	
	@PostMapping("/users")
	public ResponseEntity<Users> resgisterUser(@RequestBody Users Users) throws UserAlreadyExistException{
		return new ResponseEntity<Users>(UsersServ.registerUser(Users),HttpStatus.CREATED);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<Users>> getAllUser(){
		return new ResponseEntity<List<Users>>(UsersServ.getAllUser(), HttpStatus.OK);
	}
	
	@GetMapping("/users/{email}")
	public ResponseEntity<Users> findAUserbyId(@PathVariable String email) throws UserNotFoundException{
		return new ResponseEntity<Users>(UsersServ.findUserbyemail(email), HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{email}")
	public ResponseEntity<Users> deleteAUserbyId(@PathVariable String email) throws UserNotFoundException{
		return new ResponseEntity<Users>(UsersServ.deleteAUser(email),HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/users")
	public ResponseEntity<Users> updateAUser(@RequestBody Users updatedUser) throws UserNotFoundException{
		return new ResponseEntity<Users>(UsersServ.updateAUser(updatedUser),HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public JWTResponse login(@RequestBody JWTRequest request) throws Exception {
		
		try {
			
			manager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())	
					);
			
		}
		catch( BadCredentialsException e ){
			throw new Exception("Wrong_Username_or_Password");
		}
		
		UserDetails userdetail = userDetailService.loadUserByUsername(request.getEmail());
		
		String generateToken = jwtUtitlity.generateToken(userdetail);
		return new JWTResponse(generateToken);

		
	}
	
}
