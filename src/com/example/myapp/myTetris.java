package com.example.myapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;

import java.util.Random;


public class myTetris extends Activity  {
    /**
     * Called when the activity is first created.
     */
    boolean gama=true;
    static String message1 = "So you are trying to escape";
    static String message2 = "Are you sure ?";
    static String message3="Game over. Time in seconds :";
    static String message4="Do you want to retry";
    static String message5= "The score is :";
    static String message6= "Time :";
    int clearRowsCounter = 0;
    int TotalScore = 1000;
    int Totaltime=0;
    int delayCounter=0;
    boolean beta=true;
    TextView score, countDownText;
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

        score = (TextView) findViewById(R.id.scoreTextView);
        countDownText = (TextView) findViewById(R.id.CountDown);
        countDownText.setVisibility(View.VISIBLE);

        griedView.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                getShape().moveLeft();
                if (!isDone())
                    getShape().moveRight();
                else {
                    griedView.postInvalidate();
                }
            }

            @Override
            public void onSwipeRight() {
                getShape().moveRight();
                if (!isDone())
                    getShape().moveLeft();
                else {
                    griedView.postInvalidate();
                }
            }

            @Override
            public void onSwipeTop() {
                getShape().rotate();
                if (!isDone())
                    getShape().reverseRotate();
                else {
                    griedView.postInvalidate();
                }
            }

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
        //Intent musicServiceIntent = new Intent(this, MusicService.class);
        //startService(musicServiceIntent);

        score.setText("The score is :" + TotalScore);

        thread = new GameThread();
        thread.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public myTetris() {

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
            if (randomNum == 7)
                shapes = new WhiteShape();
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
        gama=false;
        if (keyCode == event.KEYCODE_BACK && event.getRepeatCount() == 0) {
            AlertDialog alertDialog = new
                    AlertDialog.Builder(this).create();
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
        int[][] ShapePoints = getShape().returnPoss();
        for (int i = 0; i < 4; i++) {
            int x = ShapePoints[i][0];
            int y = ShapePoints[i][1];
            griedModel.grid[y][x] = getShape().returnNum();
        }
    }

    public boolean isDone() {
        int count = 0;
        int[][] ShapePoints = getShape().returnPoss();
        for (int i = 0; i < 4; i++) {
            int x = ShapePoints[i][0];
            int y = ShapePoints[i][1];
            if (x != 22 && y != 10 && y>-1)
                if (griedModel.grid[y][x] == 0)
                    count++;
        }
        if (count == 4)
            return true;

        return false;
    }

    public int getDelay() {
        int d = 60;
        if (getModel().getClearLines() > 10 && getModel().getClearLines() <= 20)
            d = 50;
        if (getModel().getClearLines() > 20 && getModel().getClearLines() <= 30)
            d = 40;
        if (getModel().getClearLines() > 30 && getModel().getClearLines() <= 40)
            d = 30;
        if (getModel().getClearLines() > 40 && getModel().getClearLines() <= 50)
            d = 20;
        if (getModel().getClearLines() >= 60)
            d = 10;

        return d;
    }

    class GameThread extends Thread {
        boolean running = true;

        public void run() {
            while (running) {
                try {
                    if (gama) {

                        if (getModel().getClearLines() > clearRowsCounter) {
                            clearRowsCounter = getModel().getClearLines();
                            beta = true;
                            TotalScore = TotalScore + getModel().getClearLines() * 100;
                            score.setText(message5+ TotalScore);
                            griedView.postInvalidate();
                        }

                        if (randomNum == -1) {
                            shapes = getShape();
                            griedView.postInvalidate();
                        }
                        if (beta) {
                            delayCounter = getDelay();
                            countDownText.setText(message6+ getDelay());
                            delayCounter = delayCounter + 3;
                            beta = false;
                            griedView.postInvalidate();
                        } else {
                            delayCounter--;
                            countDownText.setText(message6+ delayCounter);
                            griedView.postInvalidate();
                            delayCounter++;
                        }
                        if (delayCounter == 0) {
                            if (TotalScore > 0) {
                                beta = true;
                                TotalScore = TotalScore - 100;
                                score.setText(TotalScore);
                                beta = true;
                                griedView.postInvalidate();
                            } else {
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        gama=false;
                                        alertFinished();
                                    }
                                });
                            }
                        }
                        griedView.postInvalidate();
                    }
                    if(griedModel.isGameOver()){
                        runOnUiThread(new Runnable() {
                            public void run() {
                                gama=false;
                                alertFinished();
                            }
                        });
                    }
                    Totaltime++;

                    Thread.sleep(1000);

                } catch (Exception e) {

                }
            }
        }
    }


    private void alertFinished() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(message3+Totaltime);
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
                        startActivity(new Intent(myTetris.this,MainMenu.class));
                        finish();
                    }
                });
        alertDialog.show();
    }



}

