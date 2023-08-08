package com.techelevator.dao;

import com.techelevator.model.Song;

import java.util.List;

public interface SongDao {

    List<Song> getSongs();

    Song getSongById(int id);

    Song getSongByTitle(String title);



}
