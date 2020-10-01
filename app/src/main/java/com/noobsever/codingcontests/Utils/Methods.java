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
}
