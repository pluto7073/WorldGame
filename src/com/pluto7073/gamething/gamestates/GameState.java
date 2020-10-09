package com.pluto7073.gamething.gamestates;

import com.pluto7073.gamething.Main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public abstract class GameState {

    protected GameStateManager gsm;
    public String className;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
        className = "[" + this.getClass().getSimpleName() + "]:";
        init();
    }

    public abstract void init();
    public abstract void tick() throws IOException;
    public abstract void draw(Graphics g);
    public abstract void keyPressed(int k);
    public abstract void keyReleased(int k);

}
