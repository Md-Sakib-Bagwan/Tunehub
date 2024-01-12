package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Playlist;

public interface PlaylistServices {

	public void addPlaylist(Playlist playlist);

	public List<Playlist> fetchplatlists();

}
