package com.example.peter.dependencyinjection.di;

import com.example.peter.dependencyinjection.models.LoginRepository;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
/// via component we relate
@Singleton
@Component(modules = OkHttpModule.class)
public interface OkHttpComponent {
    void inject(LoginRepository loginRepository);
}
