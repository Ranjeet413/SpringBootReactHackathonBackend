package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.Users;
import com.example.demo.service.MyUserDetailService;
import com.example.demo.service.UsersService;
import com.example.demo.util.JWTUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@WebMvcTest
@ExtendWith(MockitoExtension.class)
class UsersControllerTest {

	@MockBean
	UsersService  UServ; 
	
	@MockBean
	JWTUtility jwtUtill;
	
	@MockBean
	AuthenticationManager manager;
	
	@MockBean
	private MyUserDetailService myUserDetailService;
	
	@Autowired
	MockMvc mockMvc;
	
	
	
	ObjectMapper mapper = new ObjectMapper();
	ObjectWriter writer  = mapper.writer();

	
	@Test
	void testResgisterUser() throws Exception {
//		fail("Not yet implemented");
		Users userTosave = new Users("Aswin",(long) 1234567890,"aaswin@natwest.com",(long) 1234567890 , "Ash12345" ,"Ash12345");
		String content = writer.writeValueAsString(userTosave);
		
		when(UServ.registerUser(userTosave)).thenReturn(userTosave);
		
		mockMvc.perform(post("/users").content(content).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
//		.andExpect(jsonPath("$", Matchers.notNullValue()));
		
	}

	@Test
	void testGetAllUser() throws Exception {
		
		List<Users>UList = new LinkedList<>();
		int phno = 123456789;
		long cphno = phno; 
		
		UList.add(new Users("Aswin",(long) 1234567890,"aaswin@natwest.com",(long) 1234567890,"Ash12345" , "Ash12345"));
		UList.add(new Users("Aswin",(long) 1234567890,"aaswin@natwest.com",(long) 1234567890 , "Ash12345" , "Ash12345"));
		
		
		when(UServ.getAllUser()).thenReturn(UList);
		
		mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is(200))
		.andExpect(jsonPath("$", Matchers.hasSize(2)));
		
	}



}
