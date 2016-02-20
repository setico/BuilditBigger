package com.setico.builditbigger;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by setico on 17/02/2016.
 */
public class Utils {

    public static final String BACKEND_URL = "http://10.0.2.2:8080/_ah/api/";
    public static final String JOKE_KEY="joke";

    public static boolean isNetworkAvailable(Context c) {
        ConnectivityManager cm =
                (ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
