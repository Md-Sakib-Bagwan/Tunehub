package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Playlist;
import com.example.demo.entity.Song;
import com.example.demo.services.PlaylistServices;
import com.example.demo.services.SongServices;

@Controller
public class PlaylistController {
	@Autowired
	SongServices ss;
	
	@Autowired
	PlaylistServices ps;
	
	@GetMapping("/playlist")
	public String createPlaylist(Model model) {
		model.addAttribute("songs", ss.fetchSongs());
		return "createplaylist";
	}
	
	@PostMapping("/addplaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
		ps.addPlaylist(playlist);
		
		//update song table
		List<Song> sl=playlist.getSongs();
		
		for(Song s:sl) {
			s.getPlaylists().add(playlist);
			ss.updateSong(s);
		}
		return "adminhome";
	}
	
	@GetMapping("/viewplaylist")
	public String viewPlaylist(Model model) {
		List<Playlist> pl=ps.fetchplatlists();
		model.addAttribute("playlists",pl);
		return "viewplaylist";
	}

}
