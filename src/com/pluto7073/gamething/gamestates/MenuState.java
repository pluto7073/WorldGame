package com.pluto7073.gamething.gamestates;

import com.pluto7073.gamething.GamePanel;
import com.pluto7073.gamething.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {

    private final String[] choices = {
            "Start",
            "Options",
            "Quit"
    };

    private int currentSelection = 0;

    private String path;
    public static Color backgroundColor;

    public MenuState(GameStateManager gsm, String path, Color backgroundColor) {
        super(gsm);
        System.out.println("[MenuState]: Opening Menu");
        this.path = path;
        this.backgroundColor = backgroundColor;
        Main.f.setIconImage(new ImageIcon("C:\\Game\\assets\\images\\icon.png").getImage());
    }

    public void init() {

    }

    public void tick() {

    }

    public void draw(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        for (int i = 0; i < choices.length; i++) {
            if (currentSelection == i) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.GRAY);
            }
            g.setFont(new Font("Ariel", Font.PLAIN, 72));
            g.drawString(choices[i], GamePanel.WIDTH / 2 - 100, (i + 1) * 150 + 10);
        }
    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_DOWN) {
            currentSelection++;
            if (currentSelection > choices.length - 1) {
                currentSelection = 0;
            }
        }
        if (k == KeyEvent.VK_UP) {
            currentSelection--;
            if (currentSelection < 0) {
                currentSelection = choices.length ;
            }
        }
        if (k == KeyEvent.VK_ENTER) {
            if (currentSelection == 0) {
                gsm.states.push(new WorldChooserState(gsm, path));
            } else if (currentSelection == 1) {
                gsm.states.push(new OptionsState(gsm, backgroundColor));
            } else if (currentSelection == 2) {
                System.out.println("\nStopping!");
                System.exit(0);
            }
        }
    }

    public void keyReleased(int k) {

    }
}
