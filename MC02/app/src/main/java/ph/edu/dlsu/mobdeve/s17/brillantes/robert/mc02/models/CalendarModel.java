package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models;

import java.util.ArrayList;

public class CalendarModel {

    private ArrayList<YearModel> calendar;

    public CalendarModel() {

        int i;

        this.calendar = new ArrayList<>();

        for (i = 2021; i < 2022; i++) {
            this.calendar.add(new YearModel(i));
        }

    }

    public ArrayList<YearModel> getCalendar() {
        return calendar;
    }

    public void setCalendar(ArrayList<YearModel> calendar) {
        this.calendar = calendar;
    }
}
