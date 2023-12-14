package com.example.alg2;

public class ReadyMaze {

    private int[][] maze;

    private int startX;
    private int startY;

    private int endX;

    private int endY;

    private int curX;
    private int curY;
    private int preX;
    private int preY;

    public ReadyMaze(int[][] maze) {
        this.maze = maze;
    }

    public void startAlg() {
        showMaze();
        setXY();
        System.out.println("\n\n");
        findWay();
        showMaze();


    }

    private void findWay(){

        curX = startX;
        curY = startY;
        preX = -1;
        preY = -1;
        maze[curX][curY] = 6;

        while (true){

            if (isEnd(curX,curY)) {
                maze[curX][curY] = 6;
                return;
            }
            if (maze[curX + 1][curY] == 1) {
                maze[curX + 1][curY] = 6;
                preX = curX;
                preY = curY;
                curX++;
            } else if (maze[curX - 1][curY] == 1) {
                maze[curX - 1][curY] = 6;
                preX = curX;
                preY = curY;
                curX--;
            } else if (maze[curX][curY + 1] == 1) {
                maze[curX][curY + 1] = 6;
                preX = curX;
                preY = curY;
                curY++;
            } else if (maze[curX][curY - 1] == 1) {
                maze[curX][curY - 1] = 6;
                preX = curX;
                preY = curY;
                curY--;
            } else {
                maze[curX][curY] = 5;
                curX = preX;
                curY = preY;
                findBackWay(curX,curY);
            }
        }
    }

    private void findBackWay(int x, int y){
        if (maze[x+1][y]==6){
            preX = x+1;
            preY = y;
        } else if (maze[x-1][y]==6) {
            preX = x-1;
            preY = y;
        }else if (maze[x][y+1]==6) {
            preX = x;
            preY = y+1;
        }else if (maze[x][y-1]==6) {
            preX = x;
            preY = y-1;
        }
    }

    private boolean isEnd(int x, int y){
        if(x+1 == endX && y == endY) return true;
        if(x-1 == endX && y == endY) return true;
        if(x == endX && y+1 == endY) return true;
        if(x == endX && y-1 == endY) return true;
        return false;
    }


    private void showMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int y = 0; y < maze.length; y++) {
                if (maze[i][y] == 1 || maze[i][y] == 5) System.out.print("  ");
                if (maze[i][y] == 0) System.out.print("X ");
                if (maze[i][y] == 2) System.out.print("S ");
                if (maze[i][y] == 3) System.out.print("E ");
                if (maze[i][y] == 6) System.out.print("? ");
            }
            System.out.println();
        }
    }

    private void setXY(){
        findStartX();
        findStartY();
        findEndX();
        findEndY();
    }

    private void findStartX() {
        for (int i = 0; i < maze.length; i++) {
            for (int y = 0; y < maze.length; y++) {
                if (maze[i][y] == 2) {
                    startX = i;
                    return;
                }
            }
        }
    }

    private void findStartY(){
        for (int i = 0; i < maze.length; i++){
            if (maze[startX][i] == 2){
                startY = i;
                return;
            }
        }
    }

    private void findEndX() {
        for (int i = 0; i < maze.length; i++) {
            for (int y = 0; y < maze.length; y++) {
                if (maze[i][y] == 3) {
                    endX = i;
                    return;
                }
            }
        }
    }

    private void findEndY(){
        for (int i = 0; i < maze.length; i++){
            if (maze[endX][i] == 3){
                endY = i;
                return;
            }
        }
    }
}
