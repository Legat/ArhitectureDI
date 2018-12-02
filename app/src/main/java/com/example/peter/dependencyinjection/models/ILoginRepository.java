package com.example.peter.dependencyinjection.models;

public interface ILoginRepository {
    void login(String email, String password);
    void registration(String email, String password);
}
