package com.techelevator.dao;

import com.techelevator.model.Event;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalTime;
import java.util.List;

public class JdbcEventDaoTest extends BaseDaoTests {

    protected final static Event premadeEvent1 = new Event(1,"Event Test One",1,2,"1-1-2024","New Years Day", LocalTime.MIN,LocalTime.MIDNIGHT,
            "810 Matson Place Unit 1205, Cincinnati, OH 45204");
    protected final static Event premadeEvent2 = new Event(2,"Event Test Two",1,3,"5-12-2025","May the 12th",
            LocalTime.of(8,0),LocalTime.of(12,0),"Testing Location");

    private JdbcEventDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcEventDao(jdbcTemplate);
    }
    @Test()
    public void getEventById_ReturnsNullWhenNonexistent() {
        Assert.assertNull(sut.getEventById(0));
    }
    @Test
    public void getEventById_ReturnsCorrectEvent() {
        Event test = sut.getEventById(1);
        Assert.assertTrue("getEventById should return the correct event",this.eventsMatch(test,this.premadeEvent1));
    }
    @Test
    public void getEventsByHostId_ReturnsCorrectNumberOfEvents() {
        List<Event> test = sut.getAllEventsByHostID(2);
        Assert.assertEquals("getEventByHostId should return one event",true,test.size()==1);
        Assert.assertTrue("getEventByHostId should return the correct event",this.eventsMatch(test.get(0),this.premadeEvent1));

        List<Event> testTwo = sut.getAllEventsByHostID(3);
        Assert.assertEquals("getEventByHostId should return one event",true,testTwo.size()==1);
        Assert.assertTrue("getEventByHostId should return the correct event",this.eventsMatch(testTwo.get(0),this.premadeEvent2));
    }
    @Test
    public void getEventsByDjId_ReturnsCorrectNumberOfEvents() {
        List<Event> test = sut.getAllEventsByDJID(1);
        Assert.assertTrue("getEventByDJID should return two events",test.size()==2);
    }
    private boolean eventsMatch(Event eventOne, Event eventTwo) {
        if (
                eventOne.getName().equals(eventTwo.getName()) &&
                eventOne.getDescription().equals(eventTwo.getDescription()) &&
                eventOne.getEventId() == eventTwo.getEventId() &&
                eventOne.getHostId() == eventTwo.getHostId() &&
                eventOne.getDjId() == eventTwo.getDjId() &&
                eventOne.getLocation().equals(eventTwo.getLocation()) &&
                eventOne.getDate().equals(eventTwo.getDate()) &&
                eventOne.getStartTime() == eventTwo.getStartTime() &&
                eventOne.getEndTime() == eventTwo.getEndTime()
        ) {
            return true;
        }
        return false;
    }
}
