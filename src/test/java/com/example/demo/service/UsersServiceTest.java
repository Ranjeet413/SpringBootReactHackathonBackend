package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;
@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

	@Mock
    private UsersRepository URepo;
	
	@InjectMocks
    private UsersServiceImpl  userService;
    private Users Users,Users1;
    private List<Users> UserList;
    private Optional optional;
	
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

        Users = new Users("Aswin",(long) 1234567890,"aaswin@natwest.com",(long) 1234567890 ,"Ash12345" , "Ash12345");
        Users1 = new Users("Aswin",(long) 1234567890,"aaswin@natwest.com",(long) 1234567890 ,"Ash12345" , "Ash12345");
        optional = Optional.of(Users);
	}

	@AfterEach
	void tearDown() throws Exception {
		Users = null;
	}

	@Test
	void testRegisterUser() throws UserAlreadyExistException {
		 when(URepo.save(any())).thenReturn(Users);
	        assertEquals(Users, userService.registerUser(Users));
	        verify(URepo, times(1)).save(any());
	}



	@Test
	void testGetAllUser() {
		URepo.save(Users);

        when(URepo.findAll()).thenReturn(UserList);
        List<Users> UserList1 = userService.getAllUser();
        assertEquals(UserList, UserList1);
        verify(URepo, times(1)).save(Users);
        verify(URepo, times(1)).findAll();
	}

	@Test
	void testDeleteAUser() throws UserNotFoundException {
		 when(URepo.findById(Users.getEmail())).thenReturn(Optional.empty());
		 Users deletedNetflixUser = userService.deleteAUser("aaswin@natwest.com");
	        verify(URepo, times(1)).findById(Users.getEmail());
	}

	@Test
	void testUpdateAUser() throws UserNotFoundException {
		 when(URepo.findById(Users.getEmail())).thenReturn(optional);
	        when(URepo.save(Users)).thenReturn(Users);
	        Users.setEmail("aaswin@natwest.com");
	        Users Users1 = userService.updateAUser(Users);
	        assertEquals(Users1.getEmail(), "aaswin@natwest.com");
	        verify(URepo, times(1)).save(Users);
	        verify(URepo, times(2)).findById(Users.getEmail());
	}

}
