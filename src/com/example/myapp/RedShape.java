package com.example.myapp;

/**
 * Created by pparas on 11/02/2016.
 */
public class RedShape extends Shapes{
    int [][] points;
    int [][] temp;
    int[][][] rotateTable={
            {{0, 4}, {0, 5}, {1, 5}, {1, 6}},
            {{0, 6}, {1, 6}, {1, 5}, {2, 5}},
    };


    int index=0;

    int num;
    public RedShape(){
        num=2;
        points= new int[][]{{0, 5}, {1, 5}, {1, 4}, {2, 4}};
    }
    @Override
    public void moveLeft() {
        points[0][1]--;
        points[1][1]--;
        points[2][1]--;
        points[3][1]--;

        for(int i=0;i<rotateTable.length;i++){
            for(int j=0;j<4;j++){
                rotateTable[i][j][1]--;
            }
        }
    }

    @Override
    public void moveRight() {
        points[0][1]++;
        points[1][1]++;
        points[2][1]++;
        points[3][1]++;

        for(int i=0;i<rotateTable.length;i++){
            for(int j=0;j<4;j++){
                rotateTable[i][j][1]++;
            }
        }

    }

    @Override
    public void MoveDown() {
        points[0][0]++;
        points[1][0]++;
        points[2][0]++;
        points[3][0]++;

        for(int i=0;i<rotateTable.length;i++){
            for(int j=0;j<4;j++){
                rotateTable[i][j][0]++;
            }
        }

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
        if(index==rotateTable.length)
            index=0;
        temp=points;
        for(int i=0;i<4;i++){
            points[i][0]=rotateTable[index][i][0];
            points[i][1]=rotateTable[index][i][1];
        }
        index++;

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

    }

    @Override
    public void reverseRotate() {
        points=temp;

    }



}