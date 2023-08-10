package com.techelevator.controller;

import com.techelevator.dao.JdbcEventDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Event;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/events")
@CrossOrigin
public class EventController {

    private JdbcEventDao eventDao;

    @GetMapping(path="")
    public List<Event> listAll(@RequestParam(defaultValue = "0") int host, @RequestParam(defaultValue = "0") int dj) {
        if (host == 0 && dj == 0)
            return eventDao.getAllEvents();
        if (dj == 0)
            return eventDao.getAllEventsByHostID(host);
        return eventDao.getAllEventsByDJID(dj);
    }
}
