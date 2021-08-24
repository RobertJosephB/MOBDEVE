package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models;

import java.util.ArrayList;

public class MonthModel {

    private String              monthName;
    private ArrayList<DayModel> days;

    public MonthModel (int num) {
        int i, j = 0;

        switch (num) {
            case 0: j = 31;     this.monthName = "January";     break;
            case 1: j = 28;     this.monthName = "February";    break;
            case 2: j = 31;     this.monthName = "March";       break;
            case 3: j = 30;     this.monthName = "April";       break;
            case 4: j = 31;     this.monthName = "May";         break;
            case 5: j = 30;     this.monthName = "June";        break;
            case 6: j = 31;     this.monthName = "July";        break;
            case 7: j = 31;     this.monthName = "August";      break;
            case 8: j = 30;     this.monthName = "September";   break;
            case 9: j = 31;     this.monthName = "October";     break;
            case 10: j = 30;    this.monthName = "November";    break;
            case 11: j = 31;    this.monthName = "December";    break;
        }

        this.days = new ArrayList<>();
        for (i = 1; i <= j; i++) {
            this.days.add(new DayModel(i));
        }
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public ArrayList<DayModel> getDays() {
        return days;
    }

    public void setDays(ArrayList<DayModel> days) {
        this.days = days;
    }
}
