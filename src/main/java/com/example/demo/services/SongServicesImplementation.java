package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Song;
import com.example.demo.repository.SongRepository;

@Service
public class SongServicesImplementation implements SongServices{

	@Autowired
	SongRepository sr;
	
	public void addSong(Song song) {
		sr.save(song);
	}

	@Override
	public List<Song> fetchSongs() {
		return sr.findAll();
	}

	@Override
	public boolean songExits(String name) {
		if(sr.findByName(name)==null) {
			return false;
		}
		return true;
	}

	@Override
	public void updateSong(Song s) {
		sr.save(s);
		
	}

}
