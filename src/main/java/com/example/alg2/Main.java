package com.example.alg2;

public class Main {
    public static void main(String[] args) {

        int[][] tab= new int[10][10];

        for (int i = 0; i< tab.length; i++){
            for (int y=0; y< tab.length; y++){
                tab[i][y]= 0;
            }
        }


        tab[1][1] = 2;
        tab[1][3] = 1;
        tab[1][4] = 1;
        tab[1][5] = 1;
        tab[1][6] = 1;
        tab[1][7] = 1;
        tab[1][8] = 1;

        tab[2][1] = 1;
        tab[2][4] = 1;

        tab[3][1] = 1;
        tab[3][3] = 1;
        tab[3][4] = 1;
        tab[3][5] = 1;
        tab[3][6] = 1;
        tab[3][8] = 1;

        tab[4][1] = 1;
        tab[4][2] = 1;
        tab[4][3] = 1;
        tab[4][6] = 1;
        tab[4][8] = 1;

        tab[5][1] = 1;
        tab[5][3] = 1;
        tab[5][5] = 1;
        tab[5][6] = 1;
        tab[5][7] = 1;
        tab[5][8] = 1;

        tab[6][3] = 1;
        tab[6][5] = 1;
        tab[6][7] = 1;

        tab[7][1] = 1;
        tab[7][5] = 1;
        tab[7][7] = 1;
        tab[7][8] = 1;

        tab[8][1] = 1;
        tab[8][2] = 1;
        tab[8][3] = 1;
        tab[8][4] = 1;
        tab[8][5] = 1;

        tab[8][8] = 3;

        ReadyMaze readyMaze = new ReadyMaze(tab);
        readyMaze.startAlg();

    }
}
