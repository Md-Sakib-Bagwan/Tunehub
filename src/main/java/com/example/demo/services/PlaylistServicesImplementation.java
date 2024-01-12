package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Playlist;
import com.example.demo.repository.PlaylistRepository;

@Service
public class PlaylistServicesImplementation implements PlaylistServices{

	@Autowired
	PlaylistRepository pr;
	
	@Override
	public void addPlaylist(Playlist playlist) {
		pr.save(playlist);
	}

	@Override
	public List<Playlist> fetchplatlists() {
		return pr.findAll();
	}

}
