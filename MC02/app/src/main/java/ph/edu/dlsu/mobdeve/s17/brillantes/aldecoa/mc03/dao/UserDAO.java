package ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.dao;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.models.EventModel;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.models.UserModel;

public interface UserDAO {
    long addUser(UserModel user);
    ArrayList<UserModel> getUsers();


}
