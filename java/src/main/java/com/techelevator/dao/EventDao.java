package com.techelevator.dao;

import com.techelevator.model.Event;

import java.util.List;

public interface EventDao {

    List<Event> getAllEvents();

    List<Event> getEventByName(String name);

    List<Event> getAllEventsByHostID(int id);

    Event getEventById(int id);

    List<Event> getAllEventsByDJID(int id);

    void createEvent(Event event);

    int updateEventById(int id);

    int deleteEventById(int id);
}
