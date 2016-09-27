package com.example.myapp;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;


public class opening extends Activity {

    public void onCreate(Bundle openingxmlLayout){
        super.onCreate(openingxmlLayout);
        setContentView(R.layout.opening);
        Thread timer=new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                } finally {

                    startActivity(new Intent("android.intent.action.MAINMENU"));
                }
            }
        };
        timer.start();

    }
    protected void onPause(){
        super.onPause();
        finish();
    }
}