package com.vlad1m1r.baselibrary.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * Created by vladimirjovanovic on 10/23/15.
 */
public class NetworkUtils {

    private final Context context;

    public NetworkUtils(Context context) {
        this.context = context.getApplicationContext();
    }

    public boolean isNetworkConnected() {
        NetworkInfo ni = getNetworkInfo();
        return !(ni == null);
    }

    public boolean isConnectedWifi() {
        NetworkInfo info = getNetworkInfo();
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
    }

    public boolean isConnectedMobile() {
        NetworkInfo info = getNetworkInfo();
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
    }

    protected NetworkInfo getNetworkInfo() {
        ConnectivityManager cm = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }
}
