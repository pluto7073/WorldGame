package com.pluto7073.gamething.gamestates;

import com.pluto7073.gamething.GamePanel;
import com.pluto7073.gamething.world.World;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class WorldState extends GameState {

    private Stack<String> layers;
    private Color ground;
    private Color air;
    public String path;

    public WorldState(GameStateManager gsm, String path) {
        super(gsm);
        this.path = path;
        init();
    }

    public void init() {
        String path = this.path + "\\load.world";
        File file = new File(path);
        Scanner reader;
        try {
            reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                layers.add(data);
            }
        } catch (FileNotFoundException | NullPointerException e) {
            e.printStackTrace();
        }
        path = this.path + "\\colours\\ground.col";
        file = new File(path);
        try {
            reader = new Scanner(file);
            int i = 0;
            int
                    r = 0,
                    g = 0,
                    b = 0;
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if (i == 0) {
                    r = Integer.parseInt(data);
                } else if (i == 1)  {
                    g = Integer.parseInt(data);
                } else if (i == 2) {
                    b = Integer.parseInt(data);
                }

                i++;
            }
            ground = new Color(r, g, b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        path = this.path + "\\colours\\air.col";
        file = new File(path);
        try {
            reader = new Scanner(file);
            int i = 0;
            int
                    r = 0,
                    g = 0,
                    b = 0;
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if (i == 0) {
                    r = Integer.parseInt(data);
                } else if (i == 1)  {
                    g = Integer.parseInt(data);
                } else if (i == 2) {
                    b = Integer.parseInt(data);
                }

                i++;
            }
            air = new Color(r, g, b);
        } catch (FileNotFoundException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void drawBlocks(Graphics g) {
        int x = 0, y;
        for (y = 0; y < GamePanel.HEIGHT; y += 25) {
            String line = layers.elementAt(y);
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c == 'g') {
                    g.setColor(ground);
                    g.fillRect(i * 25, y, 25, 25);
                } else if (c == 'a') {
                    g.setColor(air);
                    g.fillRect(i * 25, y, 25, 25);
                }
            }
        }
    }

    private void drawPlayer(Graphics g) {

    }

    public void tick() {

    }

    public void draw(Graphics g) {
        drawBlocks(g);
        drawPlayer(g);
    }

    public void keyPressed(int k) {

    }

    public void keyReleased(int k) {

    }
}
