package com.noobsever.codingcontests.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class Methods {

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

    /** Method below is intended to return a List<String> for fetching the SharedPreferences */
    public static List<String> fetchTabItems(Context context) {
        ArrayList<String> savedTabItems = new ArrayList<>();

        SharedPreferences preferences = context.getSharedPreferences(Constants.TAB_ITEMS_PREFERENCES_KEY,Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonText = preferences.getString(Constants.TAB_ITEMS_ARRAYLIST_KEY, null);
        String[] text = gson.fromJson(jsonText, String[].class);
        savedTabItems.addAll(Arrays.asList(text));

        return savedTabItems;
    }

    /** Method below is intended to update the List of tab items in the SharedPreferences */
    public static void saveTabItems(Context context, List<String> list) {
        Gson gson = new Gson();
        SharedPreferences preferences = context.getSharedPreferences(Constants.TAB_ITEMS_PREFERENCES_KEY,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String text = gson.toJson(list);
        editor.putString(Constants.TAB_ITEMS_ARRAYLIST_KEY,text);
        editor.apply();
    }

    /** Method to map contest name with its website */
    public static String getSiteName(String query){
        Map<String, String> map = new HashMap<>();

        map.put(Constants.CODEFORCES,"codeforces.com");
        map.put(Constants.CODECHEF,"codechef.com");
        map.put(Constants.HACKERRANK,"topcoder.com");
        map.put(Constants.HACKEREARTH,"hackerearth.com");
        map.put(Constants.SPOJ,"spoj.com");
        map.put(Constants.ATCODER,"atcoder.jp");
        map.put(Constants.LEETCODE,"leetcode.com");
        map.put(Constants.GOOGLE,"codingcompetitions.withgoogle.com");

        return map.get(query);
    }

    public static class InternetCheck extends AsyncTask<Void, Void, Void> {


        private Activity activity;
        private InternetCheckListener listener;

        public InternetCheck(Activity x){
            activity= x;
        }

        @Override
        protected Void doInBackground(Void... params) {


            boolean b = hasInternetAccess();
            listener.onComplete(b);

            return null;
        }


        public void isInternetConnectionAvailable(InternetCheckListener x){
            listener=x;
            execute();
        }

        private boolean isNetworkAvailable() {
            ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null;
        }
        private boolean hasInternetAccess() {
            if (isNetworkAvailable()) {
                try {
                    HttpURLConnection urlc = (HttpURLConnection) (new URL("http://clients3.google.com/generate_204").openConnection());
                    urlc.setRequestProperty("User-Agent", "Android");
                    urlc.setRequestProperty("Connection", "close");
                    urlc.setConnectTimeout(1500);
                    urlc.connect();
                    return (urlc.getResponseCode() == 204 &&
                            urlc.getContentLength() == 0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Log.d("TAG", "No network available!");
            }
            return false;
        }

        public interface InternetCheckListener{
            void onComplete(boolean connected);
        }
    }


    public static String utcToLocalTimeZone(Context context,String dateStr)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);

        int hr12 = Methods.getIntPreferences(context,Constants.SWITCH_TWELVE,Constants.SWITCH_TWELVE);

        SimpleDateFormat df2;
        if(hr12==1) df2 = new SimpleDateFormat("dd-MM-yyyy | hh:mm a", Locale.ENGLISH);
        else df2 = new SimpleDateFormat("dd-MM-yyyy | HH:mm", Locale.ENGLISH);

        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        df.setTimeZone(TimeZone.getDefault());
        df2.setTimeZone(TimeZone.getDefault());
        assert date != null;

        return df2.format(date);
    }

    public static String secondToFormatted(String duration)
    {
        long dur = Long.parseLong(duration);

        long min=0,hour=0,day=0;

        min = (dur%3600)/60;
        hour = dur/3600;
        day = hour/24;
        hour= hour%24;

        String s="";

        if(day!=0)
        {
            if(hour==0 && min==0) s= s+ day +" days";
            else s= s+ day +" days, ";
        }

        if(hour==0)
        {
            if(day==0)
            {}
            else if(min==0)
            { }
            else
            {
                s+= "0 hours, ";
            }
        }else
        {
            if(min==0) s+= hour + " hours";
            else s+= hour + " hours, ";
        }

        if(min!=0)
        {
            s+= min + " minutes";
        }
        else{

            if(day==0 && hour==0)
            {
                s+="0 minutes";
            }

        }

        return s;

    }

}
