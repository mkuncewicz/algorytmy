package com.example.alg1;

import java.util.Random;

public class Maze {

    private char[][] mazeTable;
    private Random random = new Random();

    private int curX;

    private int curY;

    public Maze(int size) {
        int walls = size-1;
        this.mazeTable = new char[size+walls][size+walls];
    }

    public void startGenerator(){
        setDefault();
        setTable();
        showTable();
        System.out.println("\n\n\n");
        generate();
        showTable();
    }

    private void showTable(){

        for (int i = 0; i< mazeTable.length; i++){
            for (int y = 0; y< mazeTable.length; y++){
                if (mazeTable[i][y] == '1')System.out.print("1 ");
                if (mazeTable[i][y] == '2')System.out.print("  ");
                if (mazeTable[i][y] == '0')System.out.print("  ");
                if (mazeTable[i][y] == '9')System.out.print("X ");
            }
            System.out.println();
        }
    }


    private void setTable(){
        for (int i = 0; i< mazeTable.length; i++){
            for (int y = 0; y< mazeTable.length; y++){
                if (i % 2 == 0 && y % 2 == 0) mazeTable[i][y] = '1';
                else mazeTable[i][y] = '9';
            }
        }
    }

    private void setDefault(){
        this.curX = 0;
        this.curY = 0;
    }

    private void generate(){
        while (!allSqrVisit()){
            mazeTable[curX][curY] = '2';
            if(checkNeighborhood()){
                findAvailable();
            }
        }
    }

    private boolean checkNeighborhood(){
        int choice = random.nextInt(4)+1;
        int check = 0;

        while (check < 2){
            if (isinSize(curX+2, curY) && choice == 1){
                if (mazeTable[curX+2][curY] == '1'){
                    mazeTable[curX+1][curY] = '0';
                    curX+=2;
                    return true;
                }
            }
            if (isinSize(curX-2, curY) && choice == 2){
                if (mazeTable[curX-2][curY] == '1'){
                    mazeTable[curX-1][curY] = '0';
                    curX-=2;
                    return true;
                }
            }
            if (isinSize(curX, curY+2) && choice == 3){
                if (mazeTable[curX][curY+2] == '1'){
                    mazeTable[curX][curY+1] = '0';
                    curY+=2;
                    return true;
                }
            }
            if (isinSize(curX, curY-2) && choice == 4){
                if (mazeTable[curX][curY-2] == '1'){
                    mazeTable[curX][curY-1] = '0';
                    curX-=2;
                    return true;
                }
            }

                check++;
            }
        return false;
    }

    private void findAvailable(){
        for (int x = 0; x < mazeTable.length; x+= 2){
            for (int y = 0; y < mazeTable.length; y+= 2){
                if (mazeTable[x][y]== '1'){
                    curX = x;
                    curY = y;
                    return;
                }
            }
        }
    }

    private boolean isinSize(int x, int y){
        return x >= 0 && x < mazeTable.length && y >= 0 && y < mazeTable.length;
    }

    private boolean allSqrVisit(){
        for (int i = 0; i< mazeTable.length; i += 2){
            for (int y = 0; y < mazeTable.length; y += 2){
                if (mazeTable[i][y] == '1') return false;
            }
        }
        return true;
    }
}
