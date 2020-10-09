package com.pluto7073.gamething;

import javax.swing.*;
import java.awt.*;

public class Main extends Canvas {

    private static double v = 1.0;
    public static final JFrame f = new JFrame("Game " + v);
    public static final GamePanel panel = new GamePanel();

    public static void main(String[] args) {
        f.setIconImage(new ImageIcon("C:\\Game\\.extractedGame\\files\\images\\icon.png").getImage());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setLayout(new BorderLayout());
        f.add(BorderLayout.CENTER, panel);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

}
