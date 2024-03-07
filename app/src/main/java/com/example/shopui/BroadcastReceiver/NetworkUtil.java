package com.example.shopui.BroadcastReceiver;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {

    public static String getNetworkState(Context context){
        String status = null;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null){
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
                status = "Have internet wifi";
            }
            else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
                status = "Have internet mobile";
            }
        }
        else{
            status = "No internet";
        }
        return status;
    }
}
