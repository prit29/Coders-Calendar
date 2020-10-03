package com.noobsever.codingcontests.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

class Methods {

    public static void showToast(Context context, String message)                   //Function to show short Toast message
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(Context context, String message)               //Function to show Long Toast message
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

    public static SharedPreferences getPreferences(Context context, String key)     //Function to return SharedPreferences
    {
        return context.getSharedPreferences(key,Context.MODE_PRIVATE);
    }
    public static void setPreferences(Context context, String sharedPreferenceKey, String storeKey, String value)                           //Function to store a String in SharedPreferences
    {
        SharedPreferences.Editor sharedPreferencesEditor = context.getSharedPreferences(sharedPreferenceKey,Context.MODE_PRIVATE).edit();
        sharedPreferencesEditor.putString(storeKey,value);
        sharedPreferencesEditor.apply();
    }
    public static void setPreferences(Context context, String sharedPreferenceKey, String storeKey, int value)                 // Function to store an integer value in SharedPreferences
    {
        SharedPreferences.Editor sharedPreferencesEditor = context.getSharedPreferences(sharedPreferenceKey,Context.MODE_PRIVATE).edit();
        sharedPreferencesEditor.putInt(storeKey,value);
        sharedPreferencesEditor.apply();
    }
    public static void setPreferences(Context context, String sharedPreferenceKey, String storeKey, float value)                            //Function to store a floating point value in SharedPreferences
    {
        SharedPreferences.Editor sharedPreferencesEditor = context.getSharedPreferences(sharedPreferenceKey,Context.MODE_PRIVATE).edit();
        sharedPreferencesEditor.putFloat(storeKey,value);
        sharedPreferencesEditor.apply();
    }
    public static void setPreferences(Context context, String sharedPreferenceKey, String storeKey, long value)                              // Function to store a long value in SharedPreferences
    {
        SharedPreferences.Editor sharedPreferencesEditor = context.getSharedPreferences(sharedPreferenceKey,Context.MODE_PRIVATE).edit();
        sharedPreferencesEditor.putLong(storeKey,value);
        sharedPreferencesEditor.apply();
    }
}
