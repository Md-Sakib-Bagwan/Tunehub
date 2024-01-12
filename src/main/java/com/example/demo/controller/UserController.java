package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.services.UserServices;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	UserServices us;
	
	@PostMapping("/register")
	public String addUser(@ModelAttribute User user) {
		boolean userStatus=us.emailExits(user.getEmail());
		if(userStatus==false) {
			us.adduser(user);
		}
		else {
			System.out.println("Email is alredy present");
		}
		return "login"; 
	}
  
	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email,@RequestParam("password")String password,HttpSession session) {
		if(us.validateUser(email,password)==true) {
			String role=us.getRole(email);
			
			session.setAttribute("email", email);
			
			if(role.equals("admin")) {
				return "adminhome";
			}
			
			else {
				return "customerhome";
			}
		}
		return "login";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
}
