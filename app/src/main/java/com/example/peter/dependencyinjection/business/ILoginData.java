package com.example.peter.dependencyinjection.business;

public interface ILoginData {
    void onSuccess(String token);
    void onError(String error);
}
