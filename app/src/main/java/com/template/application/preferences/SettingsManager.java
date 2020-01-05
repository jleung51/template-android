package com.template.application.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Pair;

import java.util.Collections;
import java.util.List;

/**
 * Static class which provides simple methods to access/modify the Android Preferences.
 */
@SuppressWarnings("unused")
public class SettingsManager {

    /**
     * Sets the pre-defined default values for the Preferences in a resource file.
     *
     * @see androidx.preference.PreferenceScreen
     *
     * @param context Context of the calling Activity.
     * @param resourceId Resource of the preferences which include default values to set.
     */
    public static void setDefaults(Context context, int resourceId) {
        PreferenceManager.setDefaultValues(context, resourceId, false);
    }

    /**
     * Retrieves a string value from Preferences.
     *
     * @param context Context of the calling Activity.
     * @param key Identifier of the value to return.
     * @return The Preferences value, or an empty string if none exists.
     */
    public static String getString(Context context, String key) {
        return PreferenceManager
                .getDefaultSharedPreferences(context)
                .getString(key, "");
    }

    public static void setString(Context context, String key, String value) {
        setStrings(context, Collections.singletonList(new Pair<>(key, value)));
    }

    public static void setStrings(Context context, List<Pair<String, String>> keys_values) {
        SharedPreferences.Editor prefs = getEditableSharedPreferences(context);
        for (Pair<String, String> kv : keys_values) {
            prefs.putString(kv.first, kv.second);
        }
        prefs.apply();
    }

    /**
     * Retrieves a boolean value from Preferences.
     *
     * @param context Context of the calling Activity.
     * @param key Identifier of the value to return.
     * @return The Preferences value, or false if none exists.
     */
    public static boolean getBoolean(Context context, String key) {
        return PreferenceManager
                .getDefaultSharedPreferences(context)
                .getBoolean(key, false);
    }

    public static void setBoolean(Context context, String key, boolean b) {
        getEditableSharedPreferences(context)
                .putBoolean(key, b)
                .apply();
    }

    public static void clear(Context context) {
        getEditableSharedPreferences(context).clear().apply();
    }

    private static SharedPreferences.Editor getEditableSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).edit();
    }

}