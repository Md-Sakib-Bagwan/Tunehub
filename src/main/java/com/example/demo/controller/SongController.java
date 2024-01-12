package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Song;
import com.example.demo.entity.User;
import com.example.demo.services.SongServices;

@Controller
public class SongController {
	@Autowired
	SongServices ss;
	
	@PostMapping("/addsong")
	public String addUser(@ModelAttribute Song song) {
		boolean songStatus=ss.songExits(song.getName());
		if(songStatus==false) {
			ss.addSong(song);
		}
		else {
			System.out.println("Song is alredy present");
		}
		return "adminhome"; 
	}
	
	@GetMapping("/fetchsongs")
	public String fetchSongs(Model model) {
		List<Song> sl=ss.fetchSongs();
		model.addAttribute("songs",sl);
		return "viewsongs";
	}
	
	@GetMapping("/playsongs")
	public String playSongs(Model model) {
		boolean paymentStatus=true;
		if(paymentStatus==true) {
			List<Song> sl=ss.fetchSongs();
			model.addAttribute("songs",sl);
			return "viewsongs";
		}
		return "makepayment";
	}

}
