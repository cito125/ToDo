package com.example.andresarango.todo;


import android.app.Application;

import com.facebook.stetho.Stetho;

public class ToDoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
