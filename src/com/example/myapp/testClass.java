package com.example.myapp;

import java.util.Random;

/**
 * Created by pparas on 11/02/2016.
 */
public class testClass {
    public static void main(String[] args){
        int c=10;
        int r=22;
        int [][] gg= new int[2][4];
        int [][]temp;
        int index=0;
        for(int i=0;i<2;i++){
            for(int j=0;j<4;j++){
                gg[i][j]=index;
                index++;
            }
        }
        temp=gg;
        for(int i=0;i<2;i++){
            for(int j=0;j<4;j++){
                System.out.println(gg[i][j]);
                System.out.println(temp[i][j]);
            }
        }

        }

    }

