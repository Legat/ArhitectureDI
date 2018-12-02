package com.example.peter.dependencyinjection;

import android.app.Application;
import android.content.Context;

import com.example.peter.dependencyinjection.di.AppComponent;
import com.example.peter.dependencyinjection.di.DaggerAppComponent;
import com.example.peter.dependencyinjection.di.DaggerOkHttpComponent;
import com.example.peter.dependencyinjection.di.LoginRepositoryModel;
import com.example.peter.dependencyinjection.di.OkHttpComponent;
import com.example.peter.dependencyinjection.di.OkHttpModule;

import java.util.ArrayList;
import java.util.Arrays;

import dagger.internal.DaggerCollections;

public class App extends Application {

 //   private static OkHttpComponent componrent;
    private static AppComponent componrent;
//    public static OkHttpComponent getComponrent(){
//        return componrent;
//    }
    public static AppComponent getComponrent(){
        return componrent;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        componrent = build();
      //  context = this;
      //  list = new ArrayList(Arrays.asList("mama","papa","docha","sobaka", "cat"));
    }

//    private OkHttpComponent build(){
//    return DaggerOkHttpComponent.builder()
//                      .okHttpModule(new OkHttpModule())
//                      .build();
//    }

    private AppComponent build(){

        return DaggerAppComponent.builder()
                .okHttpModule(new OkHttpModule())
                .loginRepositoryModel(new LoginRepositoryModel())
                .build();
    }
}
