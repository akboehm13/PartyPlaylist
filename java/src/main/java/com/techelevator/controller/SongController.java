package com.techelevator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SongController {

    private SongDao songDao;

    public SongController(SongDao songDao){
        this.songDao = songDao;
    }

    @GetMapping


}
