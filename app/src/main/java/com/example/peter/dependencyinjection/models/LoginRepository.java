package com.example.peter.dependencyinjection.models;

import android.util.Log;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginRepository {
    private OkHttpClient client;
    private MediaType json;
    public static final String BASE_URL ="https://telranstudentsproject.appspot.com/_ah/api/contactsApi/v1";
    public LoginRepository(OkHttpClient client) {
        this.client = client;
        json = MediaType.parse("application/json; charset=utf-8");
    }

    public String login(String data) throws Exception {
        RequestBody body = RequestBody.create(json,data);
        Request request = new Request.Builder()
                .url(BASE_URL + "/login")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if(response.code() < 400){
            String responseData = response.body().string();
            return responseData;
        } else if(response.code() == 401){
            throw new Exception("Wrong login or password");
        }else{
            Log.d("MY_TAG", "login error " + response.body().string());
            throw new Exception("Server error");
        }

    }

    public String registration(String data) throws Exception {
        RequestBody body = RequestBody.create(json,data);
        Request request = new Request.Builder()
                .url(BASE_URL + "/registration")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if(response.code() < 400){
            String responseData = response.body().string();
            return responseData;
        } else if(response.code() == 409){
            throw new Exception("User already exist");
        }else{
            Log.d("MY_TAG", "login error " + response.body().string());
            throw new Exception("Server error");
        }

    }
}
