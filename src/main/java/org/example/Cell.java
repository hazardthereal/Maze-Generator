package org.example;

public class Cell {
    public int row, col;
    public boolean[] walls = { true, true, true, true};
    public boolean visited = false;
    public boolean current = false;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
