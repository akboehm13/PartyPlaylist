package com.techelevator.controller;

import com.techelevator.dao.JdbcEventDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Event;
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

    @GetMapping(path = "/{id}")
    public Event get(@PathVariable int id){
        Event event = eventDao.getEventById(id);
        if (event == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found");
        return event;

    }
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping(path = "/{id}")
    public Event update(@Valid @RequestBody Event event, @PathVariable int id){
        event.setEventId(id);
        try {
            Event updatedEvent = eventDao.updateEventById(event);
            return updatedEvent;
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not update event");
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "")
    public void addEvent(@Valid @RequestBody Event event) {
        try {
            eventDao.createEvent(event);
        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,"Could not create new event");
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable int id){

        if(eventDao.deleteEventById(id) == 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event was not found.");
    }

}
