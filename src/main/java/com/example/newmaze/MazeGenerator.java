package com.example.newmaze;

import java.util.*;

public class MazeGenerator {
    private final int width;
    private final int height;
    private final int[][] maze;
    private final int startX, startY, endX, endY;

    public MazeGenerator(int width, int height,int startX, int startY, int endX, int endY) {

        this.width = width;
        this.height = height;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        maze = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                maze[i][j] = 1; // Wszystkie ściany
            }
        }
        maze[startX][startY] = 2;
        maze[endX][endY] = 3;
        generateMaze(startX, startY);
        maze[endX][endY] = 3;
    }

    private void generateMaze(int x, int y) {

        DIR[] dirs = DIR.values();
        Collections.shuffle(Arrays.asList(dirs),new Random());

        for (DIR dir : dirs){
            int nextX = x + 2 * dir.dx; // Przejście o dwa pola w danym kierunku
            int nextY = y + 2 * dir.dy;

            int wallX = x + dir.dx; // Ściana pomiędzy obecnym a następnym polem
            int wallY = y + dir.dy;

//            if (maze[endX][endY] == 4) return;

            if (canMove(nextX,nextY)){
                if (maze[nextX][nextY] == 3) {
                    maze[wallX][wallY] = 0;
                    maze[nextX][nextY] = 4;
                    return;
                }

                maze[wallX][wallY] = 0;
                maze[nextX][nextY] = 0;
                generateMaze(nextX,nextY);
            }
        }
    }

    private boolean canMove(int x, int y){
        if (x >= 0 && y >= 0 && x < width && y < height && (maze[x][y] == 1 || maze[x][y] == 3)){
            if (maze[x][y] != 4) return true;
        }
        return false;
    }

    public void display() {
        for (int i = -1; i <= width; i++){
            System.out.print("# ");
        }
        System.out.println();
        for (int i = 0; i < width; i++) {
            for (int j = -1; j <= height; j++) {
                if (j == -1 || j == height) System.out.print("# ");
                else if (maze[i][j] == 0)System.out.print("  ");
                else if (maze[i][j] == 1)System.out.print("# ");
                else if (maze[i][j] == 2)System.out.print("S ");
                else if (maze[i][j] == 3)System.out.print("E ");
//                if (maze[i][j] == 4)System.out.print("E ");
            }
            System.out.println();
        }
        for (int i = -1; i <= width; i++){
            System.out.print("# ");
        }
        System.out.println();
    }

    private enum DIR {
        N(0, -1), S(0, 1), E(1, 0), W(-1, 0);
        final int dx, dy;

        DIR(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
    }
}
