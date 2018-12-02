package com.example.peter.dependencyinjection.di;

import com.example.peter.dependencyinjection.MainActivity;
import com.example.peter.dependencyinjection.models.LoginRepository;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {OkHttpModule.class,LoginRepositoryModel.class})
@Singleton
public interface AppComponent {
  void inject(LoginRepository loginRepository);
  void inject(MainActivity mainActivity);
}
