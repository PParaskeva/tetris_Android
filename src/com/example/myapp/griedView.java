package com.example.myapp ;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * For the creation of this class I was heavily inspired from the second lab
 * of the ce881
 */

public class griedView extends View implements View.OnClickListener {

    MyActivity myActivity;
    myTetris myTetris;
    boolean alpha=true;
    Shapes shape;
    griedModel model;
   // int randomNumber=6;
    int c=10;
    int r=22;
    int size;
    int x1,y1,mini,TetrisGried;

    static int gray= Color.GRAY;
    static int green= Color.GREEN;
    static int red= Color.RED;
    static int blue= Color.BLUE;
    static int yellow= Color.YELLOW;
    static int cyan= Color.CYAN;
    static int magenta=Color.MAGENTA;
    static int white=Color.WHITE;
    static int [] colorArray={gray,green,red,blue,yellow,cyan,magenta,white};

    public griedView(Context context,AttributeSet attrs) {
        super(context,attrs);
        //this.model=new griedModel();
        if (MainMenu.tetrisGame==1){
            myTetris=(myTetris) context;
        }
        if(MainMenu.tetrisGame==2) {
            myActivity = (MyActivity) context;
        }

        setOnClickListener(this);
    }


    private void setGeometry(){
        mini=Math.min(getWidth(),getHeight());
        TetrisGried=(mini/20)*10;
        size=TetrisGried/c;
        x1=(getWidth()/2)-TetrisGried/2;
        y1=(getHeight()/4)-TetrisGried/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint=new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        setGeometry();

        if (MainMenu.tetrisGame==1){
            shape = myTetris.getShape();
            model = myTetris.getModel();
        }
        if(MainMenu.tetrisGame==2) {
            shape = myActivity.getShape();
            model = myActivity.getModel();
        }
        model.isLinefull();
        for (int i=0;i<c;i++){
            for(int j=0;j<r;j++){
                int tempx=x1+size*i+size/2;
                int tempy=y1+size*j+size/2;
                if(!check(i,j))
                    paint.setColor(colorArray[model.grid[i][j]]);
                else
                    paint.setColor(colorArray[shape.returnNum()]);
                drawTitle(canvas, tempx, tempy, paint);
            }
        }
    }

    private void drawTitle(Canvas canvas, int tempx, int tempy, Paint paint) {
        int length=(size*7)/8;
        int gonia=size/6;
        int x2=tempx-length/2;
        int y2=tempy-length/2;
        RectF rect=new RectF(x2,y2,x2+length,y2+length);
        canvas.drawRoundRect(rect, gonia, gonia, paint);
    }

    private boolean check(int p1,int p2) {
        int[][] position=shape.returnPoss();
        for (int i=0;i<4;i++){
            if(position[i][0]==p2 && position[i][1]==p1){
                return true;
            }
        }

        return false;
    }

    @Override
    public void onClick(View view) {

    }

}
