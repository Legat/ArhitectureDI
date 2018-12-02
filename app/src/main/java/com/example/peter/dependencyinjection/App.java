package com.example.peter.dependencyinjection;

import android.app.Application;
import android.content.Context;

import com.example.peter.dependencyinjection.di.DaggerOkHttpComponent;
import com.example.peter.dependencyinjection.di.OkHttpComponent;
import com.example.peter.dependencyinjection.di.OkHttpModule;

import java.util.ArrayList;
import java.util.Arrays;

import dagger.internal.DaggerCollections;

public class App extends Application {
//    private static Context context;
//    private static ArrayList<String> list;
//    public static Context getContext(){
//        return context;
//    }
//
//    public static ArrayList getList() {
//        return list;
//    }
    private static OkHttpComponent componrent;

    public static OkHttpComponent getComponrent(){
        return componrent;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        componrent = build();
      //  context = this;
      //  list = new ArrayList(Arrays.asList("mama","papa","docha","sobaka", "cat"));
    }

    private OkHttpComponent build(){
    return DaggerOkHttpComponent.builder()
                      .okHttpModule(new OkHttpModule())
                      .build();
    }
}
