package com.example.myapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by pparas on 14/04/2016.
 */
public class MainMenu extends Activity implements View.OnClickListener {
    ImageView tratisional,newVersion,about;
    static int tetrisGame;
    Intent svc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuview);

        tratisional =(ImageView) findViewById(R.id.tratisionalTetrisButton);
        newVersion =(ImageView) findViewById(R.id.MyVersionTetrisButton);
        about=(ImageView) findViewById(R.id.aboutImage);

        about.setOnClickListener(this);
        tratisional.setOnClickListener(this);
        newVersion.setOnClickListener(this);

        svc=new Intent(this, BackgroundAudioService.class);
        startService(svc);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(svc);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.MyVersionTetrisButton){
            startActivity(new Intent(MainMenu.this,myTetris.class));
            tetrisGame=1;

        }
        if(view.getId()==R.id.tratisionalTetrisButton){
            startActivity(new Intent(MainMenu.this,MyActivity.class));
            tetrisGame=2;
        }
        if(view.getId()==R.id.aboutImage){
            startActivity(new Intent(MainMenu.this,AboutActivity.class));
        }

    }
}
