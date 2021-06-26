package com.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hotelbooking.api.ApiService;
import com.hotelbooking.api.RetroClient;
import com.hotelbooking.model.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

package com.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hotelbooking.api.ApiService;
import com.hotelbooking.api.RetroClient;
import com.hotelbooking.model.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminLoginActivity extends AppCompatActivity {

    EditText et_adminusername,et_adminpassword;
    TextView tvuser;
    Button btn_adminlogin;
    ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        et_adminusername = findViewById(R.id.et_adminusername);
        et_adminpassword = findViewById(R.id.et_adminpassword);
        btn_adminlogin=findViewById(R.id.btn_adminlogin);

        btn_adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_adminusername.getText().toString().isEmpty()){
                    Toast.makeText(AdminLoginActivity.this, "Please Enter Email..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(et_adminpassword.getText().toString().isEmpty()){
                    Toast.makeText(AdminLoginActivity.this, "Please Enter Password..", Toast.LENGTH_SHORT).show();
                    return;
                }
                //  startActivity(new Intent(AdminLoginActivity.this, AdminHomeActivity.class));

                adminLogin();
            }
        });

        tvuser = findViewById(R.id.tv_userlogin);

        tvuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public  void adminLogin() {
        //progreess bar loading
        loading= new ProgressDialog(AdminLoginActivity.this);
        loading.setTitle("Please wait,Data is being submit...");
        loading.show();
        // api call code
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.adminlogin(et_adminusername.getText().toString(),et_adminpassword.getText().toString());
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                loading.dismiss();
                if (response.body().status.equals("true")) {
                    startActivity(new Intent(AdminLoginActivity.this, AdminHomeActivity.class));
                    finish();
                } else {
                    Toast.makeText(AdminLoginActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(AdminLoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}