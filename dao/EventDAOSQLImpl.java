package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models.EventModel;

public class EventDAOSQLImpl implements EventDAO{

    private EventDatabase eventdatabase;
    private SQLiteDatabase database;
    public EventDAOSQLImpl(Context context){
        eventdatabase = new EventDatabase(context);

    }

    @Override
    public long addEvent(EventModel event) {
        ContentValues values = new ContentValues();

        values.put(EventDatabase.EVENT_ID,event.getEventId());
        values.put(EventDatabase.EVENT_TITLE,event.getEventTitle());
        values.put(EventDatabase.EVENT_DAY,event.getDayNumber());
        values.put(EventDatabase.EVENT_MONTH,event.getMonthName());
        values.put(EventDatabase.EVENT_YEAR,event.getYearNumber());
        values.put(EventDatabase.EVENT_TIME,event.getTime());
        values.put(EventDatabase.EVENT_DETAILS,event.getDetails());
        values.put(EventDatabase.EVENT_NOTIFICATION_TYPE,event.getNotificationType());
        values.put(EventDatabase.EVENT_NOTIFICATION_TIME,event.getNotificationTime());


        database = eventdatabase.getWritableDatabase();

        long id = database.insert(EventDatabase.TABLE_EVENTS,null,values);

        if(database!=null){
            eventdatabase.close();
        }



        return id;
    }

    @Override
    public ArrayList<EventModel> getEvents() {
        ArrayList<EventModel> result = new ArrayList<>();
        String[] columns = {EventDatabase.EVENT_ID,
                EventDatabase.EVENT_TITLE,
                EventDatabase.EVENT_DAY,
                EventDatabase.EVENT_MONTH,
                EventDatabase.EVENT_YEAR,
                EventDatabase.EVENT_TIME,
                EventDatabase.EVENT_DETAILS,
                EventDatabase.EVENT_NOTIFICATION_TYPE,
                EventDatabase.EVENT_NOTIFICATION_TIME};

        database = eventdatabase.getReadableDatabase();


        Cursor cursor = database.query(EventDatabase.TABLE_EVENTS,
                columns,
                null,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            EventModel temp = new EventModel();
            temp.setEventId(cursor.getInt(0));
            temp.setEventTitle(cursor.getString(1));
            temp.setDayNumber(cursor.getString(2));
            temp.setMonthName(cursor.getString(3));
            temp.setYearNumber(cursor.getString(4));
            temp.setTime(cursor.getString(5));
            temp.setDetails(cursor.getString(6));
            temp.setNotificationType(cursor.getString(7));
            temp.setNotificationTime(cursor.getString(8));

            result.add(temp);
            cursor.moveToNext();
        }
        if(cursor!=null){
            cursor.close();
        }
        if(database!=null){
            eventdatabase.close();
        }
        return result;
    }
    public ArrayList<EventModel> getMonthEvents(String month) {
        ArrayList<EventModel> result = new ArrayList<>();
        String[] columns = {EventDatabase.EVENT_ID,
                EventDatabase.EVENT_TITLE,
                EventDatabase.EVENT_DAY,
                EventDatabase.EVENT_MONTH,
                EventDatabase.EVENT_YEAR,
                EventDatabase.EVENT_TIME,
                EventDatabase.EVENT_DETAILS,
                EventDatabase.EVENT_NOTIFICATION_TYPE,
                EventDatabase.EVENT_NOTIFICATION_TIME};

        database = eventdatabase.getReadableDatabase();


        Cursor cursor = database.query(EventDatabase.TABLE_EVENTS,
                columns,
                null,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            EventModel temp = new EventModel();
            temp.setEventId(cursor.getInt(0));
            temp.setEventTitle(cursor.getString(1));
            temp.setDayNumber(cursor.getString(2));
            temp.setMonthName(cursor.getString(3));
            temp.setYearNumber(cursor.getString(4));
            temp.setTime(cursor.getString(5));
            temp.setDetails(cursor.getString(6));
            temp.setNotificationType(cursor.getString(7));
            temp.setNotificationTime(cursor.getString(8));

            if(temp.getMonthName().equals(month))
                result.add(temp);

            cursor.moveToNext();
        }
        if(cursor!=null){
            cursor.close();
        }
        if(database!=null){
            eventdatabase.close();
        }
        return result;
    }

    @Override
    public EventModel getEvent(int eventID) {
        EventModel event = null;

        String query = "SELECT * from " + EventDatabase.TABLE_EVENTS
                + " where " + EventDatabase.EVENT_ID + " = "+eventID;

        Cursor cursor = null;
        database = eventdatabase.getReadableDatabase();

        try{
            cursor = database.rawQuery(query,null);
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                event = new EventModel();
                event.setEventId(cursor.getInt(cursor.getColumnIndex(EventDatabase.EVENT_ID)));
                event.setEventTitle(cursor.getString(cursor.getColumnIndex(EventDatabase.EVENT_TITLE)));
                event.setDayNumber(cursor.getString(cursor.getColumnIndex(EventDatabase.EVENT_DAY)));
                event.setMonthName(cursor.getString(cursor.getColumnIndex(EventDatabase.EVENT_MONTH)));
                event.setYearNumber(cursor.getString(cursor.getColumnIndex(EventDatabase.EVENT_YEAR)));
                event.setTime(cursor.getString(cursor.getColumnIndex(EventDatabase.EVENT_TIME)));
                event.setDetails(cursor.getString(cursor.getColumnIndex(EventDatabase.EVENT_DETAILS)));
                event.setNotificationType(cursor.getString(cursor.getColumnIndex(EventDatabase.EVENT_NOTIFICATION_TYPE)));
                event.setNotificationTime(cursor.getString(cursor.getColumnIndex(EventDatabase.EVENT_NOTIFICATION_TIME)));
                cursor.moveToNext();
            }
        }
        catch (SQLiteException e){
            return null;
        }

        if(cursor!=null){
            cursor.close();
        }
        if(database!=null){
            eventdatabase.close();
        }

        return event;
    }

    @Override
    public int updateEvent(EventModel event) {
        ContentValues values = new ContentValues();

        values.put(EventDatabase.EVENT_ID,event.getEventId());
        values.put(EventDatabase.EVENT_TITLE,event.getEventTitle());
        values.put(EventDatabase.EVENT_DAY,event.getDayNumber());
        values.put(EventDatabase.EVENT_MONTH,event.getMonthName());
        values.put(EventDatabase.EVENT_YEAR,event.getYearNumber());
        values.put(EventDatabase.EVENT_TIME,event.getTime());
        values.put(EventDatabase.EVENT_DETAILS,event.getDetails());
        values.put(EventDatabase.EVENT_NOTIFICATION_TYPE,event.getNotificationType());
        values.put(EventDatabase.EVENT_NOTIFICATION_TIME,event.getNotificationTime());

        database = eventdatabase.getWritableDatabase();

        int records = database.update(EventDatabase.TABLE_EVENTS,
                values,
                EventDatabase.EVENT_ID + " = " + event.getEventId(),
                null);

        if (database != null){
            eventdatabase.close();
        }
        return records;
    }

    @Override
    public int deleteEvent(int eventID) {

        database = eventdatabase.getWritableDatabase();

        int records = database.delete(EventDatabase.TABLE_EVENTS,
                EventDatabase.EVENT_ID + " = " +  eventID,
                null);

        if (database != null){
            eventdatabase.close();
        }
        return records;
    }
}
