package com.example.shopui.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class InternetCheckServies extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String status = NetworkUtil.getNetworkState(context);
        Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
    }
}
