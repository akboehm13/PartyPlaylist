package com.techelevator.controller;


import com.techelevator.dao.JdbcEventDao;
import com.techelevator.model.Event;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Song;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/events")
@CrossOrigin
public class EventController {

    JdbcEventDao eventDao;

    public EventController(JdbcEventDao eventDao) {
        this.eventDao = eventDao;
    }


    @GetMapping(path = "")
    public List<Event> list(@RequestParam(defaultValue = "0") int host, @RequestParam(defaultValue = "0") int dj){
        if (host == 0 && dj == 0) {
            return eventDao.getAllEvents();
        } else if (dj == 0)
            return eventDao.getAllEventsByHostID(host);
        return eventDao.getAllEventsByDJID(dj);
    }

}
