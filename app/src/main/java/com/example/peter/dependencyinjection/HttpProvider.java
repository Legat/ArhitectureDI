package com.example.peter.dependencyinjection;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpProvider {
    private static final HttpProvider ourInstance = new HttpProvider();

    public static HttpProvider getInstance() {
        return ourInstance;
    }
    private OkHttpClient client;
    private MediaType json;
    public static final String BASE_URL ="https://telranstudentsproject.appspot.com/_ah/api/contactsApi/v1";
//    private HttpProvider() {
//        client = new OkHttpClient();
//    }

    private ArrayList<String> listok;
    private Context context;
    private HttpProvider(){
     //   client = client;
     //   context = App.getContext();
    //    listok = App.getList();
     client = new OkHttpClient();
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

//    public void showToast(){
//        Toast.makeText(context, "My toast", Toast.LENGTH_LONG).show();
//    }

    public String getVal(){
        return listok.get(0);
    }
}
