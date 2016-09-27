package com.example.myapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    static String message1 = "So you are trying to escape";
    static String message2 = "Are you sure ?";
    static String message3="Game over Score :";
    static String message4="Do you want to retry";

    boolean gama=true;
    public boolean running=true;
    TextView score,countDownText;
    int randomNum = 0;
    griedModel griedModel;
    griedView griedView;
    GameThread thread;


    Shapes shapes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        griedView = (griedView) findViewById(R.id.griedView);

        score =(TextView) findViewById(R.id.scoreTextView);
        countDownText = (TextView) findViewById(R.id.CountDown);
        countDownText.setVisibility(View.GONE);

        griedView.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                getShape().moveLeft();
                if (!isDone())
                    getShape().moveRight();
            }

            @Override
            public void onSwipeRight() {
                getShape().moveRight();
                if (!isDone())
                    getShape().moveLeft();
            }

            @Override
            public void onSwipeTop() {
                getShape().rotate();
                if (!isDone())
                    getShape().reverseRotate();
            }
            @Override
            public void onSwipeBottom() {
                boolean alpha = true;
                while (alpha) {
                    getShape().MoveDown();
                    if (isDone()) {
                        griedView.postInvalidate();
                    } else {
                        alpha = false;
                        getShape().MoveUp();
                        Merge();
                        shapes = null;
                        randomNum = -1;
                    }
                }
            }
        });

    }

    public void onResume() {
        super.onResume();
        thread = new GameThread();
        thread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public MyActivity() {

    }

    public void onStop() {
        super.onStop();
    }



    public Shapes getShape() {
        if (shapes == null) {
            if (randomNum > 6 || randomNum < 1)
                randomNum = randomGenerator();
            if (randomNum == 1)
                shapes = new GreenShape();
            if (randomNum == 2)
                shapes = new RedShape();
            if (randomNum == 3)
                shapes = new BlueShape();
            if (randomNum == 4)
                shapes = new YellowShape();
            if (randomNum == 5)
                shapes = new CyanShape();
            if (randomNum == 6)
                shapes = new MagnetaShape();
            if(randomNum==7)
                shapes =new WhiteShape();
        }
        return shapes;
    }

    public int randomGenerator() {
        Random rand = new Random();
        randomNum = rand.nextInt(7) + 1;
        return randomNum;
    }


    public griedModel getModel() {
        if (griedModel == null) {
            griedModel = new griedModel();
        }
        return griedModel;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK && event.getRepeatCount() == 0) {
            gama=false;

            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle(message1);
            alertDialog.setMessage(message2);

            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                    "Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int which) {

                            finish();
                        }
                    });
            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,
                    "No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            gama=true;
                        }
                    });
            alertDialog.show();
        }
        return super.onKeyDown(keyCode, event);
    }


    public void Merge() {
        int [][] ShapePoints=getShape().returnPoss();
        for (int i=0; i<4;i++) {
            int x = ShapePoints[i][0];
            int y = ShapePoints[i][1];
            griedModel.grid[y][x]=getShape().returnNum();
        }
    }

    public boolean isDone() {
        int count=0;
        int [][] ShapePoints=getShape().returnPoss();
        for (int i=0; i<4;i++){
            int x=ShapePoints[i][0];
            int y=ShapePoints[i][1];

            if(x>-1 && x<22 && y<10 && y>-1) {
                System.out.println("X :" + x + " Y :" + y);
                if (griedModel.grid[y][x] == 0)
                    count++;
            }
        }
        if (count==4)
            return true;

        return false;
    }
    public int getDelay(){
        int d=600;
        if (getModel().getClearLines()>10 && getModel().getClearLines()<=20)
            d=500;
        if (getModel().getClearLines()>20 && getModel().getClearLines()<=30)
            d=450;
        if (getModel().getClearLines()>30 && getModel().getClearLines()<=40)
            d=400;
        if (getModel().getClearLines()>40 && getModel().getClearLines()<=50)
            d=300;
        if (getModel().getClearLines()>50 && getModel().getClearLines()<=60)
            d=250;
        if (getModel().getClearLines()>60 && getModel().getClearLines()<=70)
            d=200;
        if (getModel().getClearLines()>70 && getModel().getClearLines()<=80)
            d=100;
        if (getModel().getClearLines()>80 && getModel().getClearLines()<=90)
            d=80;
        if (getModel().getClearLines()>90 && getModel().getClearLines()<=100)
            d=70;
        if (getModel().getClearLines()>100 && getModel().getClearLines()<=110)
            d=50;
        return d;
    }




    class GameThread extends Thread {
        public void run() {
            while (running) {
                try {
                    if(gama) {
                        score.setText("Clear Lines : " + getModel().getClearLines());
                        getShape().MoveDown();

                        if (isDone()) {
                            griedView.postInvalidate();
                        } else {
                            getShape().MoveUp();
                            Merge();
                            shapes = null;
                            randomNum = -1;
                        }
                        if (griedModel.isGameOver()) {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    gama=false;
                                    alertFinished();
                                }
                            });
                        }
                    }
                    Thread.sleep(getDelay());

                } catch (Exception e) {

                }
            }
        }
    }

    private void alertFinished() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(message3+griedModel.getClearLines());
        alertDialog.setMessage(message4);

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                "Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int which) {
                        getModel().resetGrid();
                        randomNum = -1;
                        shapes = null;
                        gama=true;
                        griedModel.clearLines=0;
                        griedView.postInvalidate();
                    }
                });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,
                "No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(MyActivity.this,MainMenu.class));
                        finish();
                    }
                });
        alertDialog.show();
    }
}

