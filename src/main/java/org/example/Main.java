package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int rows = 40, cols = 40;

            MazeGenerator generator = new MazeGenerator(rows, cols);
            MazePanel panel = new MazePanel(generator);

            JFrame frame = new JFrame("Maze Generator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            Timer timer = new Timer(30, e -> {
                boolean done = generator.step();
                panel.repaint();
                if (done) ((Timer) e.getSource()).stop();
            });

            timer.start();
        });
    }
}
