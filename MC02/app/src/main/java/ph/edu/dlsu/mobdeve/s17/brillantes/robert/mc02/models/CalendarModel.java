package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models;

import java.io.Serializable;
import java.util.ArrayList;

public class CalendarModel implements Serializable {

    private ArrayList<YearModel> years;

    public CalendarModel() {

        int i;

        this.years = new ArrayList<>();

        for (i = 2021; i < 2022; i++) {
            this.years.add(new YearModel(i));
        }

    }

    public ArrayList<YearModel> getYears() {
        return years;
    }

    public void setYears(ArrayList<YearModel> years) {
        this.years = years;
    }
}
