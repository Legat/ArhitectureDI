package com.example.peter.dependencyinjection;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText inputEmail, inputPassword;
    private ProgressBar progress;
    private Button regBtn, logBtn;
    private String email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputEmail = findViewById(R.id.inputemail);
        inputPassword = findViewById(R.id.inputpassword);
        progress = findViewById(R.id.progress);
        regBtn = findViewById(R.id.registration);
        logBtn = findViewById(R.id.log_btn);
        regBtn.setOnClickListener(this);
        logBtn.setOnClickListener(this);
       // inputEmail = findViewById(R.id.input);
      //  text = findViewById(R.id.text_val);
     //   String val = HttpProvider.getInstance().getVal();
     //   text.setText(val);

    }

    @Override
    protected void onResume() {
        super.onResume();
    //    HttpProvider.getInstance().showToast();
    }

    @Override
    public void onClick(View view) {
      if(view.getId() == R.id.log_btn){
         if(isValid()){
          new loginTask().execute();
         }
      } else if(view.getId() == R.id.registration){
         if(isValid()){
            new RegTask().execute();
         }
      }
    }

    private boolean isEmailValid(String email){
      return email.contains("@");
    }

    private boolean isPasswordValid(String password){
        return password.length() >=4;
    }

    private boolean isValid(){
        inputPassword.setError(null);
        inputEmail.setError(null);
        email = inputEmail.getText().toString();
        if(!isEmailValid(email)){
            inputEmail.setError("Email needs contain @");
            return false;
        }
        password = inputPassword.getText().toString();
        if(!isPasswordValid(password)){
            inputPassword.setError("Password must be more than 4 symbols");
            return false;
        }
        return true;
    }

    private void showProgress(){
        inputPassword.setVisibility(View.INVISIBLE);
        inputEmail.setVisibility(View.INVISIBLE);
        regBtn.setVisibility(View.INVISIBLE);
        logBtn.setVisibility(View.INVISIBLE);
        progress.setVisibility(View.VISIBLE);
    }

    private void hideProgress(){
        inputPassword.setVisibility(View.VISIBLE);
        inputEmail.setVisibility(View.VISIBLE);
        regBtn.setVisibility(View.VISIBLE);
        logBtn.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
    }

    private void showErrorDialog(String msg){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(msg)
                .setPositiveButton("OK",null)
                .create();

        dialog.show();
    }

   private class loginTask extends AsyncTask<Void, Void,String>{
        private boolean isSucces = true;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgress();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result = "Login ok!";
            Auth auth = new Auth(email,password);
            Gson gson = new Gson();
            String data = gson.toJson(auth);
            try {
              String responseData = HttpProvider.getInstance().login(data);
              AuthToken token = gson.fromJson(responseData, AuthToken.class);
                SharedPreferences sharedPreferences = getSharedPreferences("AUTH",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("TOKEN", token.getToken());
                editor.commit();
            } catch (IOException ex){
                ex.printStackTrace();
                result = "Connection error";
                isSucces = false;
            }
            catch (Exception e) {
                e.printStackTrace();
                result = e.getMessage();
                isSucces = false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            hideProgress();
            if(isSucces){
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }else{
                showErrorDialog(s);
            }
        }
    }

    private class RegTask extends AsyncTask<Void, Void,String>{
        private boolean isSucces = true;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgress();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result = "Registrartion ok!";
            Auth auth = new Auth(email,password);
            Gson gson = new Gson();
            String data = gson.toJson(auth);
            try {
                String responseData = HttpProvider.getInstance().registration(data);
                AuthToken token = gson.fromJson(responseData, AuthToken.class);
                SharedPreferences sharedPreferences = getSharedPreferences("AUTH",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("TOKEN", token.getToken());
                editor.commit();
            } catch (IOException ex){
                ex.printStackTrace();
                result = "Connection error";
                isSucces = false;
            }
            catch (Exception e) {
                e.printStackTrace();
                result = e.getMessage();
                isSucces = false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            hideProgress();
            if(isSucces){
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }else{
                showErrorDialog(s);
            }
        }
    }
}
