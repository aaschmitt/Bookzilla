package com.example.bookzilla;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {
    public MyApplication() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        UserProfileDataWriter.LoadAllUserInfo(this);
    }
}
