package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models;

import java.util.ArrayList;

public class DayModel {

    private String                  dayNumber;
    private ArrayList<EventModel>   events;

    public DayModel(int num) {
        this.dayNumber = "" + num;
        this.events = new ArrayList<>();
        this.events.add(new EventModel(
                this.dayNumber,
                "No events",
                "",
                "",
                "",
                ""
        ));
    }

    public String getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(String dayNumber) {
        this.dayNumber = dayNumber;
    }

    public ArrayList<EventModel> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<EventModel> events) {
        this.events = events;
    }
}
