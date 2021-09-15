package ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.dao;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.models.EventModel;

public class EventDAOFirebaseImpl implements EventDAO{

    private final String PATH = "events";
    private String userID;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference(PATH);
    String key = "";
    public EventDAOFirebaseImpl(Context context,String userID){
        final String TAG = "Listner";
        ChildEventListener childEventListener = new ChildEventListener() {

            //loaded on start
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
                EventModel event = dataSnapshot.getValue(EventModel.class);
                Log.d(TAG, "Added : " + event.getEventTitle());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());
                EventModel event = dataSnapshot.getValue(EventModel.class);
                Log.d(TAG, "Changed : " + event.getEventTitle());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());
                EventModel event = dataSnapshot.getValue(EventModel.class);
                Log.d(TAG, "Moved : " + event.getEventTitle());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "postComments:onCancelled", databaseError.toException());
                Toast.makeText(context, "Failed to load comments.",
                        Toast.LENGTH_SHORT).show();
            }
        };

        myRef.addChildEventListener(childEventListener);
        this.userID = userID;
    }
    @Override
    public long addEvent(EventModel event) {
        final long[] result = {-1};
        myRef.push().setValue(event,
            new DatabaseReference.CompletionListener(){
                @Override
                public void onComplete(DatabaseError error, DatabaseReference ref) {
                    if(error != null){
                        Log.d("ERROR","ERROR :" + error.getMessage());
                    }else{
                        Log.d("SUCCESS","DATA INSERTED");
                        result[0] =  1L;
                    }


                }
        });
        return result[0];
    }

    @Override
    public ArrayList<EventModel> getEvents() {
        ArrayList<EventModel> result = new ArrayList<>();
        myRef.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){
                    EventModel event = new EventModel();
                    event.setEventId(data.child("eventId").getValue(Integer.class));
                    event.setUserId(data.child("userId").getValue(String.class));
                    event.setEventTitle(data.child("eventTitle").getValue(String.class));
                    event.setDayNumber(data.child("dayNumber").getValue(String.class));
                    event.setMonthName(data.child("monthName").getValue(String.class));
                    event.setYearNumber(data.child("yearNumber").getValue(String.class));
                    event.setTime(data.child("time").getValue(String.class));
                    event.setDetails(data.child("details").getValue(String.class));
                    event.setNotificationType(data.child("notificationType").getValue(String.class));
                    event.setNotificationTime(data.child("notificationTime").getValue(String.class));

                    if(event.getUserId().equals(userID))
                    result.add(event);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        return result;
    }

    @Override
    public ArrayList<EventModel> getDayEvents(String month, String year, String day) {
        ArrayList<EventModel> result = new ArrayList<>();
        final int[] i = {0};
        myRef.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){

                    i[0]++;
                    EventModel event = new EventModel();
                    event.setEventId(data.child("eventId").getValue(Integer.class));
                    event.setUserId(data.child("userId").getValue(String.class));
                    event.setEventTitle(data.child("eventTitle").getValue(String.class));
                    event.setDayNumber(data.child("dayNumber").getValue(String.class));
                    event.setMonthName(data.child("monthName").getValue(String.class));
                    event.setYearNumber(data.child("yearNumber").getValue(String.class));
                    event.setTime(data.child("time").getValue(String.class));
                    event.setDetails(data.child("details").getValue(String.class));
                    event.setNotificationType(data.child("notificationType").getValue(String.class));
                    event.setNotificationTime(data.child("notificationTime").getValue(String.class));
                    if(event.getMonthName().equals(month) && event.getYearNumber().equals(year) && event.getDayNumber().equals(day)) {
                        if (event.getUserId().equals(userID))
                            result.add(event);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        return result;
    }

    @Override
    public ArrayList<EventModel> getMonthEvents(String month) {
        return null;
    }

    @Override
    public EventModel getEvent(int eventId) {
        EventModel[] temp = {new EventModel()};
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for(DataSnapshot data: snapshot.getChildren()){

                    EventModel event = new EventModel();
                    key = data.getKey();
                    event.setEventId(data.child("eventId").getValue(Integer.class));
                    event.setUserId(data.child("userId").getValue(String.class));
                    event.setEventTitle(data.child("eventTitle").getValue(String.class));
                    event.setDayNumber(data.child("dayNumber").getValue(String.class));
                    event.setMonthName(data.child("monthName").getValue(String.class));
                    event.setYearNumber(data.child("yearNumber").getValue(String.class));
                    event.setTime(data.child("time").getValue(String.class));
                    event.setDetails(data.child("details").getValue(String.class));
                    event.setNotificationType(data.child("notificationType").getValue(String.class));
                    event.setNotificationTime(data.child("notificationTime").getValue(String.class));
                    if(event.getEventId() == eventId)
                        if(event.getUserId().equals(userID))
                            temp[0] = event;

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


        return temp[0];
    }

    @Override
    public int updateEvent(EventModel event) {
        key = "";
        getEvent(event.getEventId());

        new CountDownTimer(2000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                if (!key.equals("")) {
                    myRef.child(key).child("eventId").setValue(event.getEventId());
                    myRef.child(key).child("eventTitle").setValue(event.getEventTitle());
                    myRef.child(key).child("time").setValue(event.getTime());
                    myRef.child(key).child("details").setValue(event.getDetails());
                    myRef.child(key).child("notificationType").setValue(event.getNotificationType());
                    myRef.child(key).child("notificationTime").setValue(event.getNotificationTime());
                }
            }
        }.start();
        return 1;
    }

    @Override
    public int deleteEvent(int eventId) {
        key = "";
        getEvent(eventId);

        new CountDownTimer(2000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (!key.equals("")) {
                    myRef.child(key).removeValue();
                }
            }
        }.start();
        return 1;
    }
}
