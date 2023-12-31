package com.example.alg4;

import java.util.Random;
import java.util.Stack;

public class MazeGenerator {

    private int[][] tab;

    private Stack<int[]> pathStack = new Stack<>();

    private int startX;

    private int startY;
    private int endX;

    private int endY;
    private Random random = new Random();

    public MazeGenerator(int size,int startX, int startY,int endX, int endY) {
        int walls = size -1;
        this.tab = new int[size+walls][size+walls];
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public int[][] getTab() {
        return tab;
    }

    public void startGeneratingMaze(){
        //Pierwszy etap
        createTable();
        setPoints();
        choiceWayToEnd();
        System.out.println("Pierwszy etap:");
//        showMaze();
        setStart();
        //Drug etap
        makeRestOfAre();
//        System.out.println("\n\n");
//        System.out.println("Drugi etap:");
        showMaze();

    }
    private void setStart(){
        tab[startX*2][startY*2] = 4;
    }

    private void makeRestOfAre(){

        while (!isAreFull()){
            choiceWayToRoad();
        }
    }

    private void choiceWayToRoad(){

        int[][] directions = {{2,0},{0,2},{-2,0},{0,-2}};
        int curX = findNextEmptyX();
        int curY = findNextEmptyY(curX);
        Stack<int[]> curPathStack = new Stack<>();

        boolean isFind = false;
        while (!isFind){
            boolean foundMove = false;

            if (tab[curX][curY] == 2) isFind = true;
            else tab[curX][curY] = 2;

            shuffleArray(directions);
            for (int[] dir : directions) {
                int nextX = curX + dir[0];
                int nextY = curY + dir[1];

                if (!checkLegalMove(nextX,nextY)) continue;
                if (tab[nextX][nextY] == 3 || tab[nextX][nextY] == 2) {
                    foundMove = true;
                    pathStack.push(new int[]{curX,curY});
                    int difX = (curX - nextX)/2;
                    int difY = (curY - nextY)/2;
                    tab[curX - difX][curY - difY] = 2;

                    curX = nextX;
                    curY = nextY;
                    break;
                }
            }
            if (!foundMove){
                curPathStack.pop();
                if (!curPathStack.isEmpty()){
                    int[] prePosition = curPathStack.peek();
                    curX = prePosition[0];
                    curY = prePosition[1];
                }
            }
        }

    }

    private boolean isAreFull(){

        for (int i = 0; i < tab.length; i+= 2){
            for (int y = 0; y< tab.length; y+= 2){
                if (tab[i][y] == 3) return  false;
            }
        }
        return true;
    }
    private int findNextEmptyX(){

        for (int i = 0; i<tab.length; i +=2){
            for (int y = 0; y<tab.length; y+=2){
                if (tab[i][y] == 3) return i;
            }
        }
        return -1;
    }

    private int findNextEmptyY(int x){
        if (x < 0 || x >= tab.length) return -1;
        for (int i = 0; i < tab.length; i+=2){
            if (tab[x][i] == 3) return i;
        }
        return -1;
    }

    private void choiceWayToEnd(){

        int[][] directions = {{2, 0}, {0, 2}, {-2, 0}, {0, -2}};
        int curX = startX;
        int curY = startY;
        boolean isCompletely = false;
        while (!isCompletely){
            tab[curX][curY] = 2;
            if (isEnd(curX, curY)) isCompletely = true;

            boolean foundMove = false;

            shuffleArray(directions);
            for (int[] dir : directions) {
                int nextX = curX + dir[0];
                int nextY = curY + dir[1];

                if (checkLegalMove(nextX, nextY) && tab[nextX][nextY] == 3) {
                    foundMove = true;
                    pathStack.push(new int[]{curX,curY});
                    int difX = (curX - nextX)/2;
                    int difY = (curY - nextY)/2;
                    tab[curX - difX][curY - difY] = 2;

                    curX = nextX;
                    curY = nextY;
                    break;
                }
            }
            if (!foundMove){
                pathStack.pop();
                if (!pathStack.isEmpty()){
                    int[] prePosition = pathStack.peek();
                    curX = prePosition[0];
                    curY = prePosition[1];
                }
            }

        }
    }

    private boolean isEnd(int x, int y){
        if (checkLegalMove(x+2,y)){
            if (tab[x+2][y] == 5) {
                tab[x+1][y] = 2;
                return true;
            }
        }
        if (checkLegalMove(x,y+2)) {
            if (tab[x][y + 2] == 5){
                tab[x][y+1] = 2;
                return true;
            }
        }
        if (checkLegalMove(x-2,y)) {
            if (tab[x - 2][y] == 5) {
                tab[x-1][y] = 2;
                return true;
            }
        }
        if (checkLegalMove(x,y-2)) {
            if (tab[x][y - 2] == 5){
                tab[x][y-1] = 2;
                return true;
        }
        }
        return false;
    }

    private void shuffleArray(int[][]array){
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int[] a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }

    private boolean checkLegalMove(int x, int y){
        return x >= 0 && x < tab.length && y >= 0  && y < tab.length;
    }
    private void setPoints(){
        tab[(endX*2)][(endY*2)] = 5;
    }

    private void createTable(){
        for (int i = 0; i < tab.length; i++){
            for (int y = 0; y < tab.length; y++){
                if (i % 2 == 0 && y % 2 == 0) tab[i][y] = 3;
                else tab[i][y] = 1;
            }
        }
    }

    private void showMaze(){
        for (int i = -2; i < tab.length; i++){
            System.out.print("# ");
        }
        System.out.println();
        for (int i = 0; i < tab.length; i++){
            for (int y = 0; y< tab.length; y++){
                if (y == 0) System.out.print("# ");
                if (tab[i][y] == 1) System.out.print("# ");
                if (tab[i][y] == 2) System.out.print("  ");
                if (tab[i][y] == 3) System.out.print("? ");
                if (tab[i][y] == 4) System.out.print("S ");
                if (tab[i][y] == 5) System.out.print("E ");
                if (y == tab.length-1) System.out.print("# ");
            }
            System.out.println();
        }
        for (int i = -2; i < tab.length; i++){
            System.out.print("# ");
        }
    }
}
