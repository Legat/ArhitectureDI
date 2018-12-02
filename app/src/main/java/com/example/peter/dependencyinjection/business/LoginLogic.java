package com.example.peter.dependencyinjection.business;

import com.example.peter.dependencyinjection.models.ILoginRepository;

public class LoginLogic implements ILoginData, ILoginUI {
    ILoginRepository repository;
    @Override
    public void onSuccess(String token) {

    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onLogin(String email, String password) {
        repository.login(email, password);
    }

    @Override
    public void onRegistration(String email, String password) {
        repository.registration(email,password);
    }
}
