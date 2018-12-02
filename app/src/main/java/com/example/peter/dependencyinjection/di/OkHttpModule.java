package com.example.peter.dependencyinjection.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class OkHttpModule {


    @Provides   /// wanna het this instance
    @Singleton // will be singleton
   public OkHttpClient provideOkHttpClient(){
        return new OkHttpClient();
    }
}
