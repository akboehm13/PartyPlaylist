package com.techelevator.dao;

import com.techelevator.model.PlaylistSong;
import com.techelevator.model.Song;

import java.util.List;

public interface PlaylistSongDao {

    List<Song> getSongsByPlaylistId(int id);

    List<PlaylistSong> getAll();

    void createPlaylistSong(PlaylistSong playlistSong);

}
