package com.techelevator.dao;

import com.techelevator.model.Event;

import java.util.List;

public class JdbcEventDao implements EventDao {
    @Override
    public List<Event> getAllEvents() {
        return null;
    }

    @Override
    public List<Event> getEventByName(Event event) {
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
    public Event updateEvent(Event event) {
        return null;
    }

    @Override
    public int deleteEventById(int id) {
        return 0;
    }
}
