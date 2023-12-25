package com.example.alg5;

import java.util.Random;
import java.util.Stack;

public class MazeGeneratorWithFruit {

    private int[][] tab;

    private Stack<int[]> pathStack = new Stack<>();

    private int startX;

    private int startY;
    private int endX;

    private int endY;
    private Random random = new Random();

    private int amountOfFruit;

    public MazeGeneratorWithFruit(int size, int startX, int startY, int endX, int endY) {
        int walls = size - 1;
        this.tab = new int[size + walls][size + walls];
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public int[][] getTab() {
        return tab;
    }

    public void startGeneratingMaze() {
        //Pierwszy etap
        createTable();
        setPoints();
        choiceWayToEnd();
        System.out.println("Pierwszy etap:");
        setStart();
        makeRestOfAre();
        //Drugi Etap
        setFruit();
        showMaze();
        System.out.println("\n\n");

        pickingFruit();
        showMaze();

    }

    public void startAlg2() {
        setTab();
        showMaze();
        System.out.println("\n\n");
        System.out.println("Drugi: \n");

        amountOfFruit = 7;
        pickingFruit();
        showMaze();
    }

    private void setStart() {
        tab[startX * 2][startY * 2] = 4;
    }

    private void setTab() {
        tab = new int[9][9];

        tab[0][0] = 4;
        tab[0][1] = 2;
        tab[0][2] = 2;
        tab[0][3] = 1;
        tab[0][4] = 2;
        tab[0][5] = 2;
        tab[0][6] = 2;
        tab[0][7] = 1;
        tab[0][8] = 2;


        tab[1][0] = 1;
        tab[1][1] = 1;
        tab[1][2] = 6;
        tab[1][3] = 1;
        tab[1][4] = 1;
        tab[1][5] = 1;
        tab[1][6] = 2;
        tab[1][7] = 1;
        tab[1][8] = 2;

        tab[2][0] = 2;
        tab[2][1] = 2;
        tab[2][2] = 6;
        tab[2][3] = 6;
        tab[2][4] = 2;
        tab[2][5] = 2;
        tab[2][6] = 2;
        tab[2][7] = 1;
        tab[2][8] = 2;

        tab[3][0] = 2;
        tab[3][1] = 1;
        tab[3][2] = 1;
        tab[3][3] = 1;
        tab[3][4] = 1;
        tab[3][5] = 1;
        tab[3][6] = 1;
        tab[3][7] = 1;
        tab[3][8] = 6;

        tab[4][0] = 2;
        tab[4][1] = 1;
        tab[4][2] = 2;
        tab[4][3] = 2;
        tab[4][4] = 2;
        tab[4][5] = 1;
        tab[4][6] = 2;
        tab[4][7] = 6;
        tab[4][8] = 2;

        tab[5][0] = 2;
        tab[5][1] = 1;
        tab[5][2] = 2;
        tab[5][3] = 1;
        tab[5][4] = 2;
        tab[5][5] = 1;
        tab[5][6] = 1;
        tab[5][7] = 1;
        tab[5][8] = 2;

        tab[6][0] = 2;
        tab[6][1] = 2;
        tab[6][2] = 2;
        tab[6][3] = 1;
        tab[6][4] = 2;
        tab[6][5] = 2;
        tab[6][6] = 6;
        tab[6][7] = 2;
        tab[6][8] = 2;

        tab[7][0] = 2;
        tab[7][1] = 1;
        tab[7][2] = 6;
        tab[7][3] = 1;
        tab[7][4] = 1;
        tab[7][5] = 1;
        tab[7][6] = 2;
        tab[7][7] = 1;
        tab[7][8] = 2;

        tab[8][0] = 2;
        tab[8][1] = 1;
        tab[8][2] = 2;
        tab[8][3] = 2;
        tab[8][4] = 2;
        tab[8][5] = 1;
        tab[8][6] = 5;
        tab[8][7] = 1;
        tab[8][8] = 2;
    }

    private void makeRestOfAre() {

        while (!isAreFull()) {
            choiceWayToRoad();
        }
    }

    private void choiceWayToRoad() {

        int[][] directions = {{2, 0}, {0, 2}, {-2, 0}, {0, -2}};
        int curX = findNextEmptyX();
        int curY = findNextEmptyY(curX);
        Stack<int[]> curPathStack = new Stack<>();

        boolean isFind = false;
        while (!isFind) {
            boolean foundMove = false;

            if (tab[curX][curY] == 2) isFind = true;
            else tab[curX][curY] = 2;

            shuffleArray(directions);
            for (int[] dir : directions) {
                int nextX = curX + dir[0];
                int nextY = curY + dir[1];

                if (!checkLegalMove(nextX, nextY)) continue;
                if (tab[nextX][nextY] == 3 || tab[nextX][nextY] == 2) {
                    foundMove = true;
                    pathStack.push(new int[]{curX, curY});
                    int difX = (curX - nextX) / 2;
                    int difY = (curY - nextY) / 2;
                    tab[curX - difX][curY - difY] = 2;

                    curX = nextX;
                    curY = nextY;
                    break;
                }
            }
            if (!foundMove) {
                curPathStack.pop();
                if (!curPathStack.isEmpty()) {
                    int[] prePosition = curPathStack.peek();
                    curX = prePosition[0];
                    curY = prePosition[1];
                }
            }
        }

    }

    private boolean isAreFull() {

        for (int i = 0; i < tab.length; i += 2) {
            for (int y = 0; y < tab.length; y += 2) {
                if (tab[i][y] == 3) return false;
            }
        }
        return true;
    }

    private int findNextEmptyX() {

        for (int i = 0; i < tab.length; i += 2) {
            for (int y = 0; y < tab.length; y += 2) {
                if (tab[i][y] == 3) return i;
            }
        }
        return -1;
    }

    private int findNextEmptyY(int x) {
        if (x < 0 || x >= tab.length) return -1;
        for (int i = 0; i < tab.length; i += 2) {
            if (tab[x][i] == 3) return i;
        }
        return -1;
    }

    private void choiceWayToEnd() {

        int[][] directions = {{2, 0}, {0, 2}, {-2, 0}, {0, -2}};
        int curX = startX;
        int curY = startY;
        boolean isCompletely = false;

        while (!isCompletely) {
            tab[curX][curY] = 2;
            if (isEnd(curX, curY)) isCompletely = true;

            boolean foundMove = false;

            shuffleArray(directions);
            for (int[] dir : directions) {
                int nextX = curX + dir[0];
                int nextY = curY + dir[1];

                if (checkLegalMove(nextX, nextY) && tab[nextX][nextY] == 3) {
                    foundMove = true;
                    pathStack.push(new int[]{curX, curY});
                    int difX = (curX - nextX) / 2;
                    int difY = (curY - nextY) / 2;
                    tab[curX - difX][curY - difY] = 2;

                    curX = nextX;
                    curY = nextY;
                    break;
                }
            }
            if (!foundMove) {
                pathStack.pop();
                if (!pathStack.isEmpty()) {
                    int[] prePosition = pathStack.peek();
                    curX = prePosition[0];
                    curY = prePosition[1];
                }
            }

        }
    }

    private boolean isEnd(int x, int y) {
        if (checkLegalMove(x + 2, y)) {
            if (tab[x + 2][y] == 5) {
                tab[x + 1][y] = 2;
                return true;
            }
        }
        if (checkLegalMove(x, y + 2)) {
            if (tab[x][y + 2] == 5) {
                tab[x][y + 1] = 2;
                return true;
            }
        }
        if (checkLegalMove(x - 2, y)) {
            if (tab[x - 2][y] == 5) {
                tab[x - 1][y] = 2;
                return true;
            }
        }
        if (checkLegalMove(x, y - 2)) {
            if (tab[x][y - 2] == 5) {
                tab[x][y - 1] = 2;
                return true;
            }
        }
        return false;
    }

    private void shuffleArray(int[][] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int[] a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }

    private boolean checkLegalMove(int x, int y) {
        return x >= 0 && x < tab.length && y >= 0 && y < tab.length;
    }

    private void setPoints() {
        tab[(endX * 2)][(endY * 2)] = 5;
    }

    private void createTable() {
        for (int i = 0; i < tab.length; i++) {
            for (int y = 0; y < tab.length; y++) {
                if (i % 2 == 0 && y % 2 == 0) tab[i][y] = 3;
                else tab[i][y] = 1;
            }
        }
    }

    private void showMaze() {
        for (int i = -2; i < tab.length; i++) {
            System.out.print("# ");
        }
        System.out.println();
        for (int i = 0; i < tab.length; i++) {
            for (int y = 0; y < tab.length; y++) {
                if (y == 0) System.out.print("# ");
                if (tab[i][y] == 1) System.out.print("# ");
                if (tab[i][y] == 2) System.out.print("  ");
                if (tab[i][y] == 3) System.out.print("? ");
                if (tab[i][y] == 4) System.out.print("S ");
                if (tab[i][y] == 5) System.out.print("E ");
                if (tab[i][y] == 6) System.out.print("& ");
                if (tab[i][y] == 7) System.out.print("X ");
                if (tab[i][y] == 8) System.out.print("  ");
                if (tab[i][y] == 9) System.out.print("X ");
                if (y == tab.length - 1) System.out.print("# ");
            }
            System.out.println();
        }
        for (int i = -2; i < tab.length; i++) {
            System.out.print("# ");
        }
    }

    private void setFruit() {

        amountOfFruit = random.nextInt(7) + 2;
        int curAmount = 0;

        while (curAmount < amountOfFruit) {

            int positionX = random.nextInt(tab.length);
            int positionY = random.nextInt(tab.length);

            if (tab[positionX][positionY] == 2) {
                tab[positionX][positionY] = 6;
                curAmount++;
            }

        }
    }


    private void pickingFruit() {

        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int curX = startX;
        int curY = startY;

        Stack<int[]> backPathStack = new Stack<>();

        boolean roadToFruit = false;

        int collectedFruit = 0;

        while (collectedFruit < amountOfFruit) {
            boolean foundMove = false;
            for (int[] dir : directions) {
                int nextX = curX + dir[0];
                int nextY = curY + dir[1];

                if (checkLegalMove(nextX, nextY)) {
                    if (tab[nextX][nextY] == 2 || tab[nextX][nextY] == 6) {
                        roadToFruit = false;

                        foundMove = true;
                        if (tab[nextX][nextY] == 6) {
                            collectedFruit++;
                            tab[nextX][nextY] = 9;
                        } else {
                            tab[nextX][nextY] = 7;
                        }

                        backPathStack.push(new int[]{curX, curY});


                        curX = nextX;
                        curY = nextY;
                        break;
                    }
                }
            }
            if (tab[curX][curY] == 9) roadToFruit = true;

            if (!foundMove) {
                if (!roadToFruit) tab[curX][curY] = 8;
//                backPathStack.pop();
                if (!backPathStack.isEmpty()) {
                    int[] prePosition = backPathStack.pop();
                    curX = prePosition[0];
                    curY = prePosition[1];
                }
            }
        }
    }
}

