package com.techelevator.dao;

import com.techelevator.model.Song;

import java.util.List;

public interface SongDao {

    List<Song> getAllSongs();

    List<Song> getAllSongsByGenre(String genre);

    Song getSongById(int id);

    Song getSongByTitle(String title);

    Song createSong(Song song);

    int deleteSongById(int songID);
}
