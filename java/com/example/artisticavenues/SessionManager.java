package com.example.artisticavenues;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String PREF_NAME = "YourAppSession";
    private static final String KEY_USER_ID = "";
    private static final String KEY_EMAIL = "";


    // Add more keys as needed

    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String userId, String username) {
        editor.putString(KEY_USER_ID, userId);
        editor.putString(KEY_EMAIL, username);

        editor.apply();
    }


    public String getUserId() {
        return sharedPreferences.getString(KEY_USER_ID, null);
    }

    public String getEmail() {
        return sharedPreferences.getString(KEY_EMAIL, null);
    }




    public void clearSession() {
        editor.clear();
        editor.apply();
    }
}