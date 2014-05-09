package com.micdm.nodrinking;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class TimestampManager {

    private static final String PREF_NAME = "timestamp";
    private static final int DAY_MILLISECONDS = 86400000;

    private Context context;

    public TimestampManager(Context context) {
        this.context = context;
    }

    private SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public Integer getDaysPassed() {
        long timestamp = getSharedPreferences().getLong(PREF_NAME, 0);
        return timestamp == 0 ? null : (int) Math.floor((System.currentTimeMillis() - timestamp) / DAY_MILLISECONDS);
    }

    public void createTimestamp() {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putLong(PREF_NAME, System.currentTimeMillis());
        editor.commit();
    }
}
