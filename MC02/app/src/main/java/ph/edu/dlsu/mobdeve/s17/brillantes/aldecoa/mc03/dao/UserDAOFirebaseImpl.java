package ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.dao;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.models.UserModel;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.models.UserModel;

public class UserDAOFirebaseImpl implements UserDAO {
    private final String PATH = "users";
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference(PATH);
    String key = "";
    Context context;
    public UserDAOFirebaseImpl(Context context) {
        final String TAG = "Listner";
        ChildEventListener childEventListener = new ChildEventListener() {

            //loaded on start
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
                UserModel user = dataSnapshot.getValue(UserModel.class);
                Log.d(TAG, "Added : " + user.getUserEmail());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());
                UserModel user = dataSnapshot.getValue(UserModel.class);
                Log.d(TAG, "Changed : " + user.getUserEmail());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());
                UserModel user = dataSnapshot.getValue(UserModel.class);
                Log.d(TAG, "Moved : " + user.getUserEmail());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "postComments:onCancelled", databaseError.toException());
                Toast.makeText(context, "Failed to load comments.",
                        Toast.LENGTH_SHORT).show();
            }
        };

        myRef.addChildEventListener(childEventListener);
    }

    @Override
    public long addUser(UserModel user) {
        final long[] result = {-1};
        myRef.push().setValue(user,
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
    public ArrayList<UserModel> getUsers() {
        ArrayList<UserModel> result = new ArrayList<>();

        myRef.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){
                    UserModel user = new UserModel();

                    user.setUserEmail(data.child("userEmail").getValue(String.class));
                    user.setUserPassword(data.child("userPassword").getValue(String.class));
                    user.setUserKey(data.getKey());

                    System.out.println(user.getUserEmail());
                    System.out.println(user.getUserPassword());

                    result.add(user);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        return result;
    }


}
