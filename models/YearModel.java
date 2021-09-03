package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models;

import java.io.Serializable;
import java.util.ArrayList;

public class YearModel implements Serializable {

    private String                yearNum;
    private ArrayList<MonthModel> months;

    public YearModel(int num) {
        int i;

        this.yearNum = "" + num;
        this.months = new ArrayList<>();
        for (i = 0; i < 12; i++) {
            this.months.add(new MonthModel(i));
        }
    }

    public String getYearNum() {
        return yearNum;
    }

    public void setYearNum(String yearNum) {
        this.yearNum = yearNum;
    }

    public ArrayList<MonthModel> getMonths() {
        return months;
    }

    public void setMonths(ArrayList<MonthModel> months) {
        this.months = months;
    }
}
