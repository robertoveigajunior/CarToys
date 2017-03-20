package com.example.robertoluizveigajunior.cartoys.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by robertoluizveigajunior on 18/03/17.
 */

public class Preferences {
    private SharedPreferences preferences;

    public Preferences(Context context){
        this.preferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES, 0);
    }

    public boolean isLogged(){
        return preferences.getBoolean(Constants.LOGGED, false);
    }

    public void saveLogin(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(Constants.LOGGED, true);
        editor.apply();
    }

    public void logout(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(Constants.LOGGED, false);
        editor.apply();
    }
}
