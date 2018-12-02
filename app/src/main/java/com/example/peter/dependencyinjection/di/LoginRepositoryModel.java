package com.example.peter.dependencyinjection.di;

import com.example.peter.dependencyinjection.models.LoginRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginRepositoryModel {
    @Provides
    @Singleton
    LoginRepository provideLoginRepository(){
        return new LoginRepository();
    }
}
