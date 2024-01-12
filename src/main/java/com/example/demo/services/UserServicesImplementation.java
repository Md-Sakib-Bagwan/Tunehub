package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServicesImplementation implements UserServices{

	@Autowired
	UserRepository ur;
	@Override
	public String adduser(User user) {
		ur.save(user);
		return "User is added successfully";
	}
	@Override
	public boolean emailExits(String email) {
		if(ur.findByEmail(email)==null) {
			return false;
		}
		return true;
	}
	
	public boolean validateUser(String email, String password) {
		User user=ur.findByEmail(email);
		String db_pass=user.getPassword();
		if(password.equals(db_pass)) {
			return true;
		}
		return false;
	}
	
	public String getRole(String email) {
		User user= ur.findByEmail(email);
		return user.getRole();
	}
	@Override
	public User getUser(String email) {
		return ur.findByEmail(email);
	}
	@Override
	public void updatedUser(User user) {
		ur.save(user);
		
	}

}
