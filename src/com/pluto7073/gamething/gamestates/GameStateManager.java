package com.pluto7073.gamething.gamestates;

import com.pluto7073.gamething.Main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Stack;

public class GameStateManager {

    public Stack<GameState> states;

    public GameStateManager() {
        System.out.println("[GameStateManager]: Creating Gamestates");
        states = new Stack<GameState>();
        states.push(new LoadState(this));
    }

    public void tick() {
        try {
            states.peek().tick();
        } catch (IOException | NullPointerException ignored) {

        }
    }

    public void draw(Graphics g) {states.peek().draw(g);}

    public void keyPressed(int k) {states.peek().keyPressed(k);}

    public void keyReleased(int k) {states.peek().keyReleased(k);}

}
