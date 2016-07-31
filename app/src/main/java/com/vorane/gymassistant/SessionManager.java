package com.vorane.gymassistant;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {

    // Shared preferences file name
    private static final String PREF_NAME = "UserSession";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_IS_SIGNEDIN = "isSignedIn";
    private static final String KEY_WEIGHT = "WeightMetrics";
    // Shared Preferences
    SharedPreferences pref;
    Editor editor;
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
    }

    public void setLogin(boolean isLoggedIn) {
        // commit changes
        pref.edit().putBoolean(KEY_IS_LOGGEDIN, isLoggedIn).apply();
    }

    // Check if user is logged in
    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public boolean isSignedIn() {
        return pref.getBoolean(KEY_IS_SIGNEDIN, false);
    }

    // set signed in when user signs in
    public void setSignedIn(boolean isSignedIn) {
        pref.edit().putBoolean(KEY_IS_SIGNEDIN, isSignedIn).apply();
    }

    public String weight() {
        return pref.getString(KEY_WEIGHT, "Kgs");
    }

    public void setWeightMetric(String metric) {
        pref.edit().putString(KEY_WEIGHT, metric).apply();
    }
}