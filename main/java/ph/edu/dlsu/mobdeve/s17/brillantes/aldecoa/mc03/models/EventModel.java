package ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.models;

import java.io.Serializable;

public class EventModel implements Serializable {

    private int eventId = -1;
    private String dayNumber;
    private String monthName;
    private String yearNumber;
    private String eventTitle;
    private String time;
    private String details;
    private String notificationType;
    private String notificationTime;

    public EventModel(String dayNumber, String monthName, String yearNumber, String eventTitle, String time, String details) {
        this.dayNumber = dayNumber;
        this.monthName = monthName;
        this.eventTitle = eventTitle;
        this.yearNumber = yearNumber;
        this.time = time;
        this.details = details;
        this.notificationType = notificationType;
        this.notificationTime = notificationTime;
    }

    public int getEventId() {
        return eventId;
    }
    public void setEventId(int id) {
        this.eventId = id;
    }

    public String getDayNumber() { return dayNumber; }

    public void setDayNumber(String dayNumber) { this.dayNumber = dayNumber; }

    public String getMonthName() { return monthName; }

    public void setMonthName(String monthName) { this.monthName = monthName; }

    public void setYearNumber(String yearNumber) {
        this.yearNumber = yearNumber;
    }

    public String getYearNumber() {
        return yearNumber;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) { this.notificationType = notificationType; }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) { this.notificationTime = notificationTime; }
}
