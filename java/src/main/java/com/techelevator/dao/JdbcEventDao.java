package com.techelevator.dao;

import com.techelevator.model.Event;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcEventDao implements EventDao {
    @Override
    public List<Event> getAllEvents() {
        return null;
    }

    @Override
    public List<Event> getEventByName(String name) {
        return null;
    }

    @Override
    public List<Event> getAllEventsByHostID(int id) {
        return null;
    }

    @Override
    public Event getEventById(int id) {
        return null;
    }

    @Override
    public List<Event> getAllEventsByDJID(int id) {
        return null;
    }

    @Override
    public void createEvent(Event event) {
    }

    @Override
    public int updateEventById(int id) {
        return 0;
    }

    @Override
    public int deleteEventById(int id) {
        return 0;
    }
}
