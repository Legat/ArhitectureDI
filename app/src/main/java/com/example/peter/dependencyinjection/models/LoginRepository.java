package com.example.peter.dependencyinjection.models;

import android.util.Log;

import com.example.peter.dependencyinjection.App;
import com.example.peter.dependencyinjection.business.ILoginData;
import com.example.peter.dependencyinjection.models.dao.Auth;
import com.example.peter.dependencyinjection.models.dao.AuthToken;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginRepository implements ILoginRepository{
    @Inject OkHttpClient client;
    ILoginData loginData;
    Gson gson;
    private MediaType json;
    public static final String BASE_URL ="https://telranstudentsproject.appspot.com/_ah/api/contactsApi/v1";
    public LoginRepository() {
    //    App.getComponrent().
        json = MediaType.parse("application/json; charset=utf-8");
    }

//    public String login(String data) throws Exception {
//        RequestBody body = RequestBody.create(json,data);
//        Request request = new Request.Builder()
//                .url(BASE_URL + "/login")
//                .post(body)
//                .build();
//        Response response = client.newCall(request).execute();
//        if(response.code() < 400){
//            String responseData = response.body().string();
//            return responseData;
//        } else if(response.code() == 401){
//            throw new Exception("Wrong login or password");
//        }else{
//            Log.d("MY_TAG", "login error " + response.body().string());
//            throw new Exception("Server error");
//        }
//
//    }
//
//    public String registration(String data) throws Exception {
//        RequestBody body = RequestBody.create(json,data);
//        Request request = new Request.Builder()
//                .url(BASE_URL + "/registration")
//                .post(body)
//                .build();
//        Response response = client.newCall(request).execute();
//        if(response.code() < 400){
//            String responseData = response.body().string();
//            return responseData;
//        } else if(response.code() == 409){
//            throw new Exception("User already exist");
//        }else{
//            Log.d("MY_TAG", "login error " + response.body().string());
//            throw new Exception("Server error");
//        }
//
//    }

    @Override
    public void login(String email, String password) {
        Auth auth = new Auth(email,password);
        String data = gson.toJson(auth);
        RequestBody body = RequestBody.create(json,data);
        Request request = new Request.Builder()
                .url(BASE_URL + "/login")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(response.code() <= 400){
                String responseJson = response.body().string();
                AuthToken token = gson.fromJson(responseJson, AuthToken.class);
                loginData.onSuccess(token.getToken());
            }else if(response.code() == 401){
                loginData.onError("Wrong login or password");
            }else{
                loginData.onError("Server Error");
                Log.d("MY_TAG", "login error " + body.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            loginData.onError("Connection error");
        }
    }

    @Override
    public void registration(String email, String password) {
        Auth auth = new Auth(email,password);
        String data = gson.toJson(auth);
        RequestBody body = RequestBody.create(json,data);
        Request request = new Request.Builder()
                .url(BASE_URL + "/registration")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(response.code() <= 400){
                String responseJson = response.body().string();
                AuthToken token = gson.fromJson(responseJson, AuthToken.class);
                loginData.onSuccess(token.getToken());
            }else if(response.code() == 409){
                loginData.onError("User already exist!");
            }else{
                loginData.onError("Server Error");
                Log.d("MY_TAG", "login error " + body.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            loginData.onError("Connection error");
        }
    }
}
