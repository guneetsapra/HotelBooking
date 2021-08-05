package com.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hotelbooking.adapters.messagesadapter;
import com.hotelbooking.api.ApiService;
import com.hotelbooking.api.RetroClient;
import com.hotelbooking.model.Chat;
import com.hotelbooking.model.ResponseData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminMessageActivity extends AppCompatActivity {


    ApiService apiService;
    String frm;
    String eto;
    String pid="3";
    List<Chat> msg=new ArrayList<Chat>();

    EditText msgtext;
    ProgressDialog pd;
    Button send;
    com.hotelbooking.adapters.messagesadapter messagesadapter;
    Runnable r;
    RecyclerView recyclerView;
    Handler h=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_message);
        msgtext=(EditText)findViewById(R.id.msgtext);
        send=(Button)findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(msgtext.getText().toString().isEmpty())
                {
                    Toast.makeText(AdminMessageActivity.this,"Please enter message", Toast.LENGTH_SHORT).show();
                }
                sendMessage(getIntent().getStringExtra("from"),getIntent().getStringExtra("to"),getIntent().getStringExtra("hid"));
            }
        });

        Toast.makeText(AdminMessageActivity.this,getIntent().getStringExtra("hid").toString()+getIntent().getStringExtra("from").toString()+getIntent().getStringExtra("to").toString(), Toast.LENGTH_SHORT).show();
        frm=getIntent().getStringExtra("from");
        eto=getIntent().getStringExtra("to");
        Log.d("chat",frm+""+eto);
        recyclerView=findViewById(R.id.messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        messagesadapter=new messagesadapter(msg,frm,AdminMessageActivity.this);
        recyclerView.setAdapter(messagesadapter);

        r =new Runnable() {
            @Override
            public void run() {
                h.postDelayed(r,10000);
                getmessages();


            }
        };

        h.post(r);


    }

    public void getmessages(){
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Chat>> call = service.getchat(frm,eto,getIntent().getStringExtra("hid"));
        call.enqueue(new Callback<List<Chat>>() {
            @Override
            public void onResponse(Call<List<Chat>> call, Response<List<Chat>> response) {
                if(response.body()==null){
                    Toast.makeText(AdminMessageActivity.this,"No data found", Toast.LENGTH_SHORT).show();
                }else {
                    // pd.dismiss();
                    if(response.body().size()>0) {
                        Log.d("hotels", response.toString());
                        msg.clear();
                        msg.addAll(response.body());
                        Log.d("hotel", msg.toString());
                        messagesadapter.notifyDataSetChanged();
                        //messagesadapter.notifyDataSetChanged();
                        recyclerView.smoothScrollToPosition(msg.size() - 1);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Chat>> call, Throwable t) {

            }
        });
    }
    public  void sendMessage(final String frm, final  String eto, final String hid) {
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.adminmsgchat(frm,hid,msgtext.getText().toString());
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                getmessages();
                msgtext.setText("");
                if (response.body().message.equals("true")) {

                    Toast.makeText(AdminMessageActivity.this, response.body().message, Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(AdminMessageActivity.this, response.body().message, Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }
}