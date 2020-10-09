package com.pluto7073.gamething.gamestates;

import com.pluto7073.gamething.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class OptionsState extends GameState {

    private String optionsFile;
    private String[] options = {
            "Change Color",
            "Back"
    };
    private Color backgroundColor = new Color(0, 0, 0);
    private int currentSelection = 0;

    public OptionsState(GameStateManager gsm, Color backgroundColor) {
        super(gsm);
        File file = new File("C:\\Game\\main.path");
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                data = data + "\\options\\options.txt";
                optionsFile = data;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.backgroundColor = backgroundColor;
    }

    public void init() {

    }

    public void tick() {

    }

    public void draw(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        g.setFont(new Font("Ariel", Font.PLAIN, 72));
        for (int i = 0; i < options.length; i++) {
            if (i == currentSelection) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.GRAY);
            }
            g.drawString(options[i], GamePanel.WIDTH / 2 - 300, (i + 1) * 150 + 10);
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
            if (currentSelection <= -1) {
                currentSelection = options.length;
            }
        }
        if (k == KeyEvent.VK_ENTER) {
            if (currentSelection == 0) {
                gsm.states.push(new ColorGameState(gsm, backgroundColor));
            } else if (currentSelection == 1) {
                gsm.states.pop();
            }
        }
    }

    public void keyReleased(int k) {

    }
}
