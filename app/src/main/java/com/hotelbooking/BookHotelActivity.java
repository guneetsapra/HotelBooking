package com.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.hotelbooking.adapters.RoomsAdapter;
import com.hotelbooking.api.ApiService;
import com.hotelbooking.api.RetroClient;
import com.hotelbooking.model.ResponseData;
import com.hotelbooking.model.RoomsPojo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookHotelActivity extends AppCompatActivity {

    EditText et_name,et_age,et_date,etNameonCard,etCardNumber,etExapDate,etCvvumber;
    Spinner sp_room,sp_days,sp_payment,sp_dyroom;
    Button btn_confirm,btn_payment;
    LinearLayout card;
    SharedPreferences sharedPreferences;
    String sessionname;
    ProgressDialog pd;
    String hotel_id;
    View view;
    int mYear, mMonth, mDay;
    String DAY, MONTH, YEAR;
    ArrayList<String> type;
    ProgressDialog loading;
    List<RoomsPojo> hotelInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_hotel);
        getSupportActionBar().setTitle("Book Now");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedPreferences = getApplicationContext().getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        sessionname = sharedPreferences.getString("user_name", "def-val");

        hotel_id=(getIntent().getStringExtra("hid"));
        Toast.makeText(BookHotelActivity.this, hotel_id, Toast.LENGTH_SHORT).show();


        et_name=(EditText)findViewById(R.id.et_name);
        et_age=(EditText)findViewById(R.id.et_age);
        et_date=(EditText)findViewById(R.id.et_date);

        et_date.setFocusable(false);
        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bdate();
            }
        });

        sp_dyroom=(Spinner) findViewById(R.id.sp_dyroom);
        type=new ArrayList<>();

        getRooms();

        etNameonCard=(EditText)findViewById(R.id.etNameonCard);
        etCardNumber=(EditText)findViewById(R.id.etCardNumber);
        etExapDate=(EditText)findViewById(R.id.etExapDate);
        etCvvumber=(EditText)findViewById(R.id.etCvvumber);
        sp_room=(Spinner) findViewById(R.id.sp_room);
        sp_days=(Spinner) findViewById(R.id.sp_days);
        sp_payment=(Spinner) findViewById(R.id.sp_payment);
        card=(LinearLayout) findViewById(R.id.card);
        btn_confirm=(Button) findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(et_name.getText().toString().isEmpty()){
                    Toast.makeText(BookHotelActivity.this, "Please Enter Name..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(et_age.getText().toString().isEmpty()){
                    Toast.makeText(BookHotelActivity.this, "Please Enter Age..", Toast.LENGTH_SHORT).show();
                    return;
                }

                card.setVisibility(View.VISIBLE);
            }
        });
        btn_payment=(Button) findViewById(R.id.btn_payment);
        btn_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etNameonCard.getText().toString().isEmpty()){
                    Toast.makeText(BookHotelActivity.this, "Enter Name..", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(etCardNumber.getText().toString().isEmpty()){
                    Toast.makeText(BookHotelActivity.this, "Enter Age..", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if(etExapDate.getText().toString().isEmpty()){
                    Toast.makeText(BookHotelActivity.this, "Enter Expire Date..", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(etCvvumber.getText().toString().isEmpty()){
                    Toast.makeText(BookHotelActivity.this, "Enter Cvv..", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    bookHotel();
                }
            }
        });


    }

    private void bookHotel() {
        String name = et_name.getText().toString();
        String age = et_age.getText().toString();
        String bdate = et_date.getText().toString();
        String room = sp_dyroom.getSelectedItem().toString();
        String days = sp_days.getSelectedItem().toString();
        String payment = sp_payment.getSelectedItem().toString();
        String cardname = etNameonCard.getText().toString();
        String cardno = etCardNumber.getText().toString();
        String cardexp = etExapDate.getText().toString();
        String cardcvv = etCvvumber.getText().toString();
        String email = sessionname;
        String hid = hotel_id;

        pd = new ProgressDialog(BookHotelActivity.this);
        pd.setMessage("Payment Processing....");
        pd.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.book(name,age,bdate,room,days,payment,cardname,cardno,cardexp,cardcvv,email,hid);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                if (response.body().status.equals("true")) {
                    Toast.makeText(BookHotelActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(BookHotelActivity.this, UserHomeActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(BookHotelActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(BookHotelActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void bdate() {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(BookHotelActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        DAY = dayOfMonth + "";
                        MONTH = monthOfYear + 1 + "";
                        YEAR = year + "";

                        et_date.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        datePickerDialog.show();
    }


    public void getRooms() {
        loading = new ProgressDialog(BookHotelActivity.this);
        loading.setMessage("Loading....");
        loading.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<RoomsPojo>> call = service.getRooms(getIntent().getStringExtra("hid"));
        call.enqueue(new Callback<List<RoomsPojo>>() {
            @Override
            public void onResponse(Call<List<RoomsPojo>> call, Response<List<RoomsPojo>> response) {
                loading.dismiss();
                //   Toast.makeText(AdminHotelsActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                if (response.body() == null) {
                    Toast.makeText(BookHotelActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    hotelInfo = response.body();
                   // sp_dyroom.setAdapter(new RoomsAdapter(hotelInfo, BookHotelActivity.this));
                    Log.d("room",hotelInfo.get(0).getType());

                    showListinSpinner();
                }
            }
            @Override
            public void onFailure(Call<List<RoomsPojo>> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(BookHotelActivity.this, "Something went wrong...Please contact admin !", Toast.LENGTH_SHORT).show();
            }
        });
    }



    //Our method to show list
    private void showListinSpinner(){
        //String array to store all the book names
        String[] items = new String[hotelInfo.size()];

        //Traversing through the whole list to get all the names
        for(int i=0; i<hotelInfo.size(); i++){
            //Storing names to string array
            items[i] = hotelInfo.get(i).getType();
        }

        //Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(BookHotelActivity.this, android.R.layout.simple_list_item_1, items);
        //setting adapter to spinner
        sp_dyroom.setAdapter(adapter);
        //Creating an array adapter for list view

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