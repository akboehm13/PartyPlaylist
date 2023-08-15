package com.techelevator.dao;

import com.techelevator.model.Playlist;

import java.util.List;

public interface PlaylistDao {
    List<Playlist> getPlaylistsByEventId(int eventId);

    List<Playlist> getAllPlaylists();

    Playlist getPlaylistById(int id);

    Playlist updatePlaylist(Playlist playlist);

    Playlist createPlaylist(Playlist playlist);
}
