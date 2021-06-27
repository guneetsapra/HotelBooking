package com.hotelbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int ScreenDisplay = 4000;
        Thread waittimer=new Thread(){
            int waiting=0;
            public void run(){
                try{
                    while(waiting<=ScreenDisplay )
                    {
                        sleep(100);
                        waiting+=100;
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                finally{
                    Intent i= new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();

                }
            }
        };
        waittimer.start();
    }
}
