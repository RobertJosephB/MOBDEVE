package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models;

import java.io.Serializable;
import java.util.ArrayList;

public class DayModel implements Serializable {

    private String                  dayNum;
    private String                  monthName;
    private String                  yearNum;
    private ArrayList<EventModel>   events;

    public DayModel(int num, String monthName) {
        this.dayNum = "" + num;
        this.monthName = monthName;
        this.events = new ArrayList<>();
        this.events.add(new EventModel(
                this.dayNum,
                this.monthName,
                this.yearNum,
                "No events",
                "",
                ""
        ));
    }

    public String getDayNumber() {
        return dayNum;
    }

    public void setDayNumber(String dayNumber) {
        this.dayNum = dayNumber;
    }

    public String getMonthName() { return monthName; }

    public void setMonthName(String monthName) { this.monthName = monthName; }

    public ArrayList<EventModel> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<EventModel> events) {
        this.events = events;
    }

    public String getYearNum() {
        return yearNum;
    }

    public void setYearNum(String yearNum) {
        this.yearNum = yearNum;
    }
}
