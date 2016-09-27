package com.example.myapp;

import java.io.Serializable;

/**
 * Created by pparas on 26/01/2016.
 */
public class griedModel implements Serializable {
    int [][] grid;
    int clearLines;
    int c;
    int r;

    public griedModel(){
        clearLines=0;
        c=10;
        r=22;
        grid=new int[c][r];
    }

    public void isLinefull(){
        int count=0;

        for (int i=0;i<r;i++){
            count=0;
            for(int j=0;j<c;j++){
                if (grid[j][i]!=0){
                    count++;
                }
                if (count==c){
                    clearRow(i);
                    MoveBlocksOneRowDown(i);
                    ClearLinesIncreased();
                }
            }
        }
    }

    private void MoveBlocksOneRowDown(int column) {
        if(column!=0) {
            for (int j = 0; j < c; j++) {
                grid[j][column] = grid[j][column - 1];
                grid[j][column - 1] = 0;
            }
        }

        if (column!=0){
            MoveBlocksOneRowDown(column-1);
        }
    }

    public void clearRow(int column){
        for(int j=0;j<c;j++){
            grid[j][column]=0;
        }
    }

    public boolean isGameOver(){
        for (int i=0;i<c;i++){
            if(grid[i][0]!=0)
                return true;
        }
        return false;
    }

    public int[][] returnArray(){
        return grid;
    }

    public void  resetGrid(){
        grid=new int[c][r];
    }

    public void ClearLinesIncreased(){
        clearLines++;
    }

    public int getClearLines(){
        return clearLines;
    }

}
