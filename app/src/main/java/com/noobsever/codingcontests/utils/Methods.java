package com.noobsever.codingcontests.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Alankrita on 01/10/20.
 */
public class Methods {
    /**
     * You can use this method directly from anywhere in your project when you want to toast a message.
     * Default set length is long.
     * To use this, call Methods.toastMessage(context, msg)
     */
    public static void toastMessage(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    //To display a toast for a shorter span, use Methods.toastMessageShort(context, msg)
    public static void toastMessageShort(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
