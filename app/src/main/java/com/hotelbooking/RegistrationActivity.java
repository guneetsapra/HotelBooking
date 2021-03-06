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

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(et_name.getText().toString().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please Enter Name..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(et_username.getText().toString().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please Enter email..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(et_phone.getText().toString().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please Enter Phone..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(et_password.getText().toString().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please Enter Password..", Toast.LENGTH_SHORT).show();
                    return;
                }
                userRegistration();


            }
        });
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }

    private void userRegistration() {
        String name = et_name.getText().toString();
        String phone = et_phone.getText().toString();
        String email = et_username.getText().toString();
        String pass = et_password.getText().toString();

        pd = new ProgressDialog(RegistrationActivity.this);
        pd.setMessage("Loading....");
        pd.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.user_registration(name,email,pass,phone);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                if (response.body().status.equals("true")) {
                    Toast.makeText(RegistrationActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(RegistrationActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(RegistrationActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}