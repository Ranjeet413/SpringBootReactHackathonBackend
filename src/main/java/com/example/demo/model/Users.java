package com.example.demo.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Data
@Table

public class Users {
	
	@Id
	private String email;
	private String name;
	private Long accountNo;	
	private Long mobileNo;
	private String password;
	
	


	public Users() {
		super();
		
	}

	

	public Users(String name, Long accountNo, String email, Long mobileNo, String password,
			String confirmpassword) {
		super();
		this.name = name;
		this.accountNo = accountNo;
		this.email = email;
		this.mobileNo = mobileNo;
		this.password = password;
		;
	}

	


	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Long getAccountNo() {
		return accountNo;
	}



	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Long getMobileNo() {
		return mobileNo;
	}



	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}





	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		String encryptedPwd = pwdEncoder.encode(password);
		this.password = encryptedPwd;
	}
	

	
	public void setNewPassword(String password) {
//		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
//		String encryptedPwd = pwdEncoder.encode(password);
		this.password = password;
	}


	@Override
	public String toString() {
		return "Users [name=" + name + ", accountNo=" + accountNo + ", email=" + email + ", mobileNo=" + mobileNo
				+ ", password=" + password + ",]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(accountNo,email, mobileNo, name, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		return Objects.equals(accountNo, other.accountNo) && 
				Objects.equals(email, other.email) && Objects.equals(mobileNo, other.mobileNo)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password);
				
	}
	
	
	
	
	
	
	
}
