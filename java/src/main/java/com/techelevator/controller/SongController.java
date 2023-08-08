package com.techelevator.controller;

import com.techelevator.dao.SongDao;
import com.techelevator.model.Song;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.ServerEndpoint;
import java.util.List;

@RestController
@RequestMapping("/songs")
@CrossOrigin
public class SongController {

    private SongDao songDao;

    public SongController(SongDao songDao){
        this.songDao = songDao;
    }

    @GetMapping(path = "")
    public List<Song> list(@RequestParam(defaultValue = "") String genre){
        if (!genre.equals("")){
            return songDao.getAllSongsByGenre(genre);
        }
         return songDao.getAllSongs();
    }

    @GetMapping(path = "/{id}")
    public Song get(@PathVariable int id){
        Song song = songDao.getSongById(id);
        if (song == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Song Not Found");
        } else {
            return songDao.getSongById(id);
        }
    }

}
