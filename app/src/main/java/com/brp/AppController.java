package com.brp;

import android.app.Application;
import android.content.Context;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class AppController extends Application {


    private Context mContext;
    private static AppController appController;
    FirebaseRemoteConfig mFirebaseRemoteConfig ;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        appController=this;
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
//        checkUpdate();
    }

    public Context getContext() {
        return mContext;
    }

    public static synchronized AppController getInstance(){
        return appController;
    }

}