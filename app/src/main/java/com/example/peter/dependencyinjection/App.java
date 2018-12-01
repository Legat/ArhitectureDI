package com.example.peter.dependencyinjection;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;

public class App extends Application {
    private static Context context;
    private static ArrayList<String> list;
    public static Context getContext(){
        return context;
    }

    public static ArrayList getList() {
        return list;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        list = new ArrayList(Arrays.asList("mama","papa","docha","sobaka", "cat"));
    }
}
