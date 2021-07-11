package com.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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

public class ForgotPasswordActivity extends AppCompatActivity {
    String uname;
    EditText et_email;
    Button btn_retrievepassword;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        et_email = findViewById(R.id.et_email);
        btn_retrievepassword = findViewById(R.id.btn_retrievepassword);
        getSupportActionBar().setTitle("Forget Password");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        uname=sharedPreferences.getString("user_name","");

        btn_retrievepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Enter your Email",Toast.LENGTH_LONG).show();
                    return;
                }
                forgotPassword();
            }
        });

    }
    public  void forgotPassword()
    {
        String email=et_email.getText().toString();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.getPassword(email);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.body().message.equals("true")) {
                    startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
                    finish();
                    Toast.makeText(ForgotPasswordActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                   // Log.i("sample", "" + response.body().message);

                } else {
                    Toast.makeText(ForgotPasswordActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Log.d("sample","Response = "+t.toString());
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
