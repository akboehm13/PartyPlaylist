package com.techelevator.controller;

import com.techelevator.dao.JdbcPlaylistSong;
import com.techelevator.model.Event;
import com.techelevator.model.PlaylistSong;
import com.techelevator.model.Song;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/playlist-song")
@CrossOrigin
public class PlaylistSongController {
    JdbcPlaylistSong plSongDao;

    public PlaylistSongController(JdbcPlaylistSong plSongDao){
        this.plSongDao = plSongDao;
    }
    @GetMapping(path = "")
    public List<PlaylistSong> getAll(){
        return plSongDao.getAll();
    }
    @GetMapping(path = "/{id}")
    public List<Song> songList(@PathVariable int id){
        List<Song> songs = plSongDao.getSongsByPlaylistId(id);

        if (songs == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist Not Found");
        }
        return songs;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "")
    public void addEvent(@Valid @RequestBody PlaylistSong playlistSong) {
        try {
            plSongDao.createPlaylistSong(playlistSong);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,"Could not create new playlist-song");
        }
    }
}
