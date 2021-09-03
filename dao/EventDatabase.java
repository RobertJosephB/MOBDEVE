package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_EVENTS = "events";
    public static final String EVENT_ID = "id";
    public static final String EVENT_TITLE = "title";
    public static final String EVENT_DAY = "day";
    public static final String EVENT_MONTH = "month";
    public static final String EVENT_YEAR = "year";
    public static final String EVENT_TIME = "time";
    public static final String EVENT_DETAILS = "details";
    public static final String EVENT_NOTIFICATION_TYPE = "type";
    public static final String EVENT_NOTIFICATION_TIME = "ntime";

    public static final String CREATE_EVENT_TABLE =
            "create table " + TABLE_EVENTS + " ( "
                    + EVENT_ID + " integer primary key, "
                    + EVENT_TITLE +" text, "
                    + EVENT_DAY +" text, "
                    + EVENT_MONTH +" text, "
                    + EVENT_YEAR +" text, "
                    + EVENT_TIME +" text, "
                    + EVENT_DETAILS +" text, "
                    + EVENT_NOTIFICATION_TYPE +" text, "
                    + EVENT_NOTIFICATION_TIME +" text ); ";

    public EventDatabase(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_EVENT_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_EVENTS);
        onCreate(db);
    }
}
