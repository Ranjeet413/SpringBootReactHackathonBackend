package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Users;

@SpringBootTest
class UsersRepositoryTest {

	
	@Autowired
	UsersRepository URepo; 
	Users Users;

	List<Users> UsersList = new LinkedList<>();
	
	@BeforeEach
	void setUp() throws Exception {
		Users = new Users();
		Users.setName("Aswin");
		Users.setAccountNo((long) 1234567890);
		Users.setEmail("aaswin50@gmail.com");			
		Users.setMobileNo((long) 1234567890);
		Users.setPassword("Hello123!");
		
	}

	@AfterEach
	void tearDown() throws Exception {
		URepo.deleteAll();
		URepo = null;
		
	}

	@Test
	void testFindAll() {
		
		Users Users = new Users("Aswin",(long) 1234567890,"aaswin@natwest.com",(long) 1234567890 ,"Ash12345" , "Ash12345");
		Users Users1 = new Users("Bharath",(long) 1234567890,"bharath@natwest.com",(long) 1234567890 , "Bha12345" , "Bha12345");
		URepo.save(Users);
		URepo.save(Users1);

        List<Users> blogList = (List<Users>)  URepo.findAll();
        assertEquals("bharath@natwest.com", blogList.get(1).getEmail());
	}

	@Test
	void testSave() {
		URepo.save(Users);
		Users fetchedUsers =URepo.findById(Users.getName()).get();
        assertEquals(5, fetchedUsers.getName().length());
	}

	@Test
	void testFindById() {
		Users Users = new Users("Aswin",(long) 1234567890,"aaswin@natwest.com",(long) 1234567890 , "Ash12345" , "Ash12345");
		Users Users1 = URepo.save(Users);
        Optional<Users> optional = URepo.findById(Users1.getName());        
        assertEquals(Users1.getName(), optional.get().getName());
        assertEquals(Users1.getAccountNo(), optional.get().getAccountNo());
        assertEquals(Users1.getEmail(), optional.get().getEmail());
        assertEquals(Users1.getMobileNo(), optional.get().getMobileNo());
        assertEquals(Users1.getPassword(), optional.get().getPassword());
        
	}

}
