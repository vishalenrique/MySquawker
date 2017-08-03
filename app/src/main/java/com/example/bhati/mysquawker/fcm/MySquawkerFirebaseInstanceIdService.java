package com.example.bhati.mysquawker.fcm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Bhati on 8/2/2017.
 */

public class MySquawkerFirebaseInstanceIdService extends FirebaseInstanceIdService {
    public static final String TAG=MySquawkerFirebaseInstanceIdService.class.getSimpleName();
    @Override
    public void onTokenRefresh() {
        String token=FirebaseInstanceId.getInstance().getToken();
        Log.v(TAG,token);
    }
}
