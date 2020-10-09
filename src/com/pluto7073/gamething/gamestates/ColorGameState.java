package com.pluto7073.gamething.gamestates;

import com.pluto7073.gamething.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ColorGameState extends GameState {

    private int r;
    private int g;
    private int b;
    private String colorPath  = "";
    private String[] options = {
            "r",
            "g",
            "b",
            "[info]"
    };
    private int currentSelection = 0;
    private Color backgroundColour = new Color(0, 0, 0);

    public ColorGameState(GameStateManager gsm, Color backgroundColour) {
        super(gsm);
        this.backgroundColour = backgroundColour;
        try {
            File file = new File("C:\\Game\\main.path");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                colorPath = data + "\\options\\colours.txt";
            }
            System.out.println(className + " " + colorPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void init() {

    }

    public void tick() {
        r = backgroundColour.getRed();
        g = backgroundColour.getGreen();
        b = backgroundColour.getBlue();
    }

    private boolean refreshColours(String path, int r, int g, int b) {
        File file = new File(path);
        boolean d = file.delete();
        if (d) {
            System.out.println(className + " Deleted Colour File, Creating New Colour File...");
        } else {
            System.out.println(className + " Failed to delete colour.txt, terminating method...");
            return false;
        }
        File newFile = new File(path);
        try {
            if (newFile.createNewFile()) {
                try {
                    FileWriter writer = new FileWriter(file);
                    writer.write("ColorR=" + r + "\n" +
                            "ColorG=" + g + "\n" +
                            "ColorB=" + b);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void draw(Graphics g) {
        g.setColor(backgroundColour);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        for (int i = 0; i < options.length; i++) {
            g.setFont(new Font("Ariel", Font.PLAIN, 72));
            if (i == currentSelection) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.GRAY);
            }
            if (options[i].equals("r")) {
                g.drawString("" + r, GamePanel.WIDTH / 2 - 300, (i + 1) * 100 + 10);
            } else if (options[i].equals("g")) {
                g.drawString("" + this.g, GamePanel.WIDTH / 2 - 300, (i + 1) * 100 + 10);
            } else if (options[i].equals("b")) {
                g.drawString("" + b, GamePanel.WIDTH / 2 - 300, (i + 1) * 100 + 10);
            } else if (options[i].equals("[info]")) {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Ariel", Font.PLAIN, 14));
                g.drawString("Use Dowm/Up Arrows to Select, Enter to change. \n Enter a Number 0 - 255 \n Hit Escape to Exit and Save", GamePanel.WIDTH / 2 - 300, GamePanel.HEIGHT - 100);
            }
        }
    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_DOWN) {
            currentSelection++;
            if (currentSelection > options.length - 1) {
                currentSelection = 0;
            }
        }
        if (k == KeyEvent.VK_UP) {
            currentSelection--;
            if (currentSelection <= 0) {
                currentSelection = options.length;
            }
        }
        if (k == KeyEvent.VK_ESCAPE) {
            boolean refreshed = false;
            while (!refreshed) {
                refreshed = refreshColours(colorPath, 50, 50, 50);
            }
            gsm.states.push(new LoadState(gsm));
        }
    }

    public void keyReleased(int k) {

    }
}
