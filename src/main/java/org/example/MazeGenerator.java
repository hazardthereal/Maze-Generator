package org.example;

import java.util.*;

public class MazeGenerator{
    public int col;
    public int row;
    public final Cell[][] grid;
    private final Stack<Cell> stack = new Stack<>();
    private final Random random = new Random();
    private Cell current;

    public MazeGenerator(int row, int col) {
        this.row = row;
        this.col = col;
        grid = new Cell[row][col];
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < col; x++) {
                grid[y][x] = new Cell(y, x);
            }
        }
        current = grid[0][0];
        current.visited = true;
    }

    public boolean step() {
        current.current = false;
        Cell next = getUnvisitedNeighbor(current);

        if (next != null) {
            next.visited = true;
            stack.push(current);
            removeWalls(current, next);
            current = next;
            current.current = true;
        } else if (!stack.isEmpty()) {
            current = stack.pop();
            current.current = true;
        } else {
            return true;
        }
        return false;
    }

    private Cell getUnvisitedNeighbor(Cell cell) {
        List<Cell> bro = new ArrayList<>();
        int broRow = cell.row, broCol = cell.col;

        if (broRow > 0 && !grid[broRow - 1][broCol].visited) bro.add(grid[broRow - 1][broCol]);     // top
        if (broCol < col - 1 && !grid[broRow][broCol + 1].visited) bro.add(grid[broRow][broCol + 1]); // right
        if (broRow < row - 1 && !grid[broRow + 1][broCol].visited) bro.add(grid[broRow + 1][broCol]); // bottom
        if (broCol > 0 && !grid[broRow][broCol - 1].visited) bro.add(grid[broRow][broCol - 1]);     // left

        if (bro.isEmpty()) return null;
        return bro.get(random.nextInt(bro.size()));
    }

    private void removeWalls(Cell a, Cell b) {
        int dx = b.col - a.col;
        int dy = b.row - a.row;

        if (dx == 1) { a.walls[1] = false; b.walls[3] = false; }
        if (dx == -1) { a.walls[3] = false; b.walls[1] = false; }
        if (dy == 1) { a.walls[2] = false; b.walls[0] = false; }
        if (dy == -1) { a.walls[0] = false; b.walls[2] = false; }
    }
}
