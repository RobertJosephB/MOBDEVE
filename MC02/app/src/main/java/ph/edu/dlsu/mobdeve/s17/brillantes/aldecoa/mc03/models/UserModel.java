package ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.models;

import java.io.Serializable;

public class UserModel implements Serializable {

    private String userEmail;
    private String userPassword;
    private String userKey;




    public UserModel(String userEmail,String userPassword) {

      this.userEmail = userEmail;
      this.userPassword = userPassword;
    }

    public UserModel() {


    }



    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }



    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getUserKey() {
        return userKey;
    }
}
