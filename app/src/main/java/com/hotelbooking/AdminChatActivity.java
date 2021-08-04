package com.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.hotelbooking.adapters.AdminChatAdapter;
import com.hotelbooking.api.ApiService;
import com.hotelbooking.api.RetroClient;
import com.hotelbooking.model.Chat;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminChatActivity extends AppCompatActivity {

    ListView list_view;
    ProgressDialog progressDialog;
    List<Chat> msgs;
    SharedPreferences sharedPreferences;
    AdminChatAdapter myRequestAdapter;
    String session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_chat);
        getSupportActionBar().setTitle("Chat Messages");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view=(ListView)findViewById(R.id.list_chat);
        msgs= new ArrayList<>();
        serverData();
    }
    public void serverData(){
        progressDialog = new ProgressDialog(AdminChatActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Chat>> call = service.userchats("admin@gmail.com");
        call.enqueue(new Callback<List<Chat>>() {
            @Override
            public void onResponse(Call<List<Chat>> call, Response<List<Chat>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(AdminChatActivity.this,"No data found", Toast.LENGTH_SHORT).show();
                }else {
                    msgs = response.body();
                    myRequestAdapter=new AdminChatAdapter(msgs,AdminChatActivity.this);  //attach adapter class with therecyclerview
                    list_view.setAdapter(myRequestAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Chat>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(AdminChatActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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