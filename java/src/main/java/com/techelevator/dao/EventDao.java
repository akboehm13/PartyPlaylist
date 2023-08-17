package com.techelevator.dao;

import com.techelevator.model.Event;

import java.util.List;

public interface EventDao {

    List<Event> getAllEvents();

    Event getEventByName(String name);

    List<Event> getAllEventsByHostID(int id);

    Event getEventById(int id);

    List<Event> getAllEventsByDJID(int id);

    Event createEvent(Event event);

    Event updateEventById(Event event);

    int deleteEventById(int id);
}
