package com.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hotelbooking.api.ApiService;
import com.hotelbooking.api.RetroClient;
import com.hotelbooking.model.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    EditText et_name, et_username, et_phone, et_password;
    Button btn_signup, btn_login;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        et_name = findViewById(R.id.et_name);
        et_username = findViewById(R.id.et_username);
        et_phone = findViewById(R.id.et_phone);
        et_password = findViewById(R.id.et_password);

        btn_signup = findViewById(R.id.btn_signup);


    }
}