package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.util;


import android.content.Context;
import android.content.SharedPreferences;

public class StoragePreferences {

    private SharedPreferences appPreferences;
    private final String PREFS = "appPreferences";

    public StoragePreferences(Context context){
        appPreferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public void saveStringPreferences(String key, String value){
        SharedPreferences.Editor prefsEditor = appPreferences.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public String getStringPreferences(String key){
        return (appPreferences.getString(key, "Nothing Saved"));
    }
}
