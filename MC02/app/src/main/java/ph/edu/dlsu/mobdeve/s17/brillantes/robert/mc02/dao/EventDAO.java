package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.dao;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models.EventModel;

public interface EventDAO {
    long addEvent(EventModel event);
    ArrayList<EventModel> getEvents();

    ArrayList<EventModel> getDayEvents(String month,String year, String day);
    EventModel getEvent(int eventId);
    int updateEvent(EventModel event);
    int deleteEvent(int eventId);

}
