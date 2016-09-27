package com.example.myapp;


import java.io.Serializable;

public abstract class Shapes implements Serializable {
    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract void MoveDown();
    public abstract void MoveUp();
    public abstract void rotate();
    public abstract int[][] returnPoss();
    public abstract int returnNum();
    public abstract void setArray(int [][] array);
    public abstract void reverseRotate();
}
