package org.example;

import javax.swing.*;
import java.awt.*;

public class MazePanel  extends JPanel {
    private int width = 800;
    private int height = 800;
    private final MazeGenerator maze;
    private final int cellSize = 20;

    public MazePanel(MazeGenerator maze) {
        this.maze = maze;
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.darkGray);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Cell[][] grid = maze.grid;

        for (int y = 0; y < maze.row; y++) {
            for (int x = 0; x < maze.col; x++) {
                Cell cell = grid [x][y];
                int px = x * cellSize;
                int py = y * cellSize;

                if (cell.visited) {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(px, py, cellSize, cellSize);
                }

                if (cell.current) {
                    g.setColor(Color.GREEN);
                    g.fillRect(px, py, cellSize, cellSize);
                }

                g.setColor(Color.BLACK);
                if (cell.walls[0]) g.drawLine(px, py, px + cellSize, py);
                if (cell.walls[1]) g.drawLine(px + cellSize, py, px + cellSize, py + cellSize);
                if (cell.walls[2]) g.drawLine(px, py + cellSize, px + cellSize, py + cellSize);
                if (cell.walls[3]) g.drawLine(px, py, px, py + cellSize);
            }

        }
    }
}
