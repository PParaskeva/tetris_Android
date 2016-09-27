package com.example.myapp;

/**
 * Created by pparas on 11/02/2016.
 */
public class MagnetaShape extends Shapes {
    int [][] points;
    int num;

    public MagnetaShape(){
        num=6;
        points= new int[][]{{0, 4}, {0, 5}, {1, 4}, {1, 5}};
    }

    @Override
    public void moveLeft() {
        points[0][1]--;
        points[1][1]--;
        points[2][1]--;
        points[3][1]--;

    }

    @Override
    public int[][] returnPoss() {
        return points;
    }

    @Override
    public int returnNum() {
        return num;
    }

    @Override
    public void setArray(int[][] array) {
        points=array;
    }

    @Override
    public void reverseRotate() {

    }


    @Override
    public void moveRight() {
        points[0][1]++;
        points[1][1]++;
        points[2][1]++;
        points[3][1]++;


    }

    @Override
    public void MoveDown() {

        points[0][0]++;
        points[1][0]++;
        points[2][0]++;
        points[3][0]++;
    }

    @Override
    public void MoveUp() {
        points[0][0]--;
        points[1][0]--;
        points[2][0]--;
        points[3][0]--;

    }

    @Override
    public void rotate() {

    }



}
