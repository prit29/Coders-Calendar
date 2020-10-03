package com.noobsever.codingcontests.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

class Methods {

    /**Function to show short Toast message*/
    public static void showToast(Context context, String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    /**Function to show Long Toast message*/
    public static void showLongToast(Context context, String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

    /**This function returns the String value of a given key stored in SharedPreferences.
       In case if preference doesn't exist, empty string is returned
     */
    public static String getStringPreferences(Context context, String sharedPreferenceKey, String storeKey)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharedPreferenceKey,Context.MODE_PRIVATE);
        return  sharedPreferences.getString(storeKey,"");
    }

    /**This function returns the integer value of a given key stored in SharedPreferences.
        In case if preference doesn't exist, 0 is returned
     */
    public static int getIntPreferences(Context context, String sharedPreferenceKey, String storeKey)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharedPreferenceKey,Context.MODE_PRIVATE);
        return  sharedPreferences.getInt(storeKey,0);
    }

    /**This function returns the floating point value of a given key stored in SharedPreferences.
       In case if preference doesn't exist, 0.0f is returned
     */
    public static float getFloatPreferences(Context context, String sharedPreferenceKey, String storeKey)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharedPreferenceKey,Context.MODE_PRIVATE);
        return  sharedPreferences.getFloat(storeKey,0.0f);
    }

    /**This function returns the long value of a given key stored in SharedPreferences.
        In case if preference doesn't exist, 0 is returned.
     */
    public static long getLongPreferences(Context context, String sharedPreferenceKey, String storeKey)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharedPreferenceKey,Context.MODE_PRIVATE);
        return  sharedPreferences.getLong(storeKey,0);
    }

    /** Function to store a String value in SharedPreferences*/
    public static void setPreferences(Context context, String sharedPreferenceKey, String storeKey, String value)
    {
        SharedPreferences.Editor sharedPreferencesEditor = context.getSharedPreferences(sharedPreferenceKey,Context.MODE_PRIVATE).edit();
        sharedPreferencesEditor.putString(storeKey,value);
        sharedPreferencesEditor.apply();
    }

    /** Function to store an integer value in SharedPreferences*/
    public static void setPreferences(Context context, String sharedPreferenceKey, String storeKey, int value)
    {
        SharedPreferences.Editor sharedPreferencesEditor = context.getSharedPreferences(sharedPreferenceKey,Context.MODE_PRIVATE).edit();
        sharedPreferencesEditor.putInt(storeKey,value);
        sharedPreferencesEditor.apply();
    }

    /** Function to store a float value in SharedPreferences*/
    public static void setPreferences(Context context, String sharedPreferenceKey, String storeKey, float value)
    {
        SharedPreferences.Editor sharedPreferencesEditor = context.getSharedPreferences(sharedPreferenceKey,Context.MODE_PRIVATE).edit();
        sharedPreferencesEditor.putFloat(storeKey,value);
        sharedPreferencesEditor.apply();
    }

    /** Function to store a long value in SharedPreferences*/
    public static void setPreferences(Context context, String sharedPreferenceKey, String storeKey, long value)
    {
        SharedPreferences.Editor sharedPreferencesEditor = context.getSharedPreferences(sharedPreferenceKey,Context.MODE_PRIVATE).edit();
        sharedPreferencesEditor.putLong(storeKey,value);
        sharedPreferencesEditor.apply();
    }
}
