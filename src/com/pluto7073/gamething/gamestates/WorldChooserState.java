package com.pluto7073.gamething.gamestates;

import com.pluto7073.gamething.GamePanel;
import com.pluto7073.gamething.world.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;


public class WorldChooserState extends GameState {

    private static boolean worldsLoaded = false;
    private static String path;
    private String currentState = "list";
    private boolean first = false;
    private String totalName = "";
    private boolean load = false;
    private Stack<World> worlds;
    private boolean list = false;
    private int currentSelection = 0;
    private String[] strings = {
            "", "", "", "", ""
    };
    private boolean loadFirst = false;

    public WorldChooserState(GameStateManager gsm, String path) {
        super(gsm);
        System.out.println(className + " Loading world Chooser...");
        this.path = path;
        init();
    }

    private void loadWorlds(String path) {
        path = "C:\\Game\\.game\\worldsaves";
        String path1 = path + "\\new.world";
        File file = new File(path);
        File f = new File(path1);
        if (f.exists()) {
            first = true;
            return;
        }
        currentState = "list";
        File[] folders = file.listFiles();
        boolean b = false;
        for (int i = 0; i < folders.length; i++) {
            File cw = folders[i];
            if (cw.isDirectory()) {
                File[] ficw = cw.listFiles();
                for (int x = 0; i < ficw.length; i++) {
                    File cf = ficw[x];
                    if (cf.getName().equals("load.world")) {
                        b = true;
                    }
                    if (b) {
                        World world = new World(cw.getName(), path);
                        worlds.push(world);
                        strings[i] = world.getWorldName();
                    }
                }
            }
        }
        list = true;
    }

    private void loadWorld(File path) {
        String name = strings[currentSelection];
        World world = new World(name, "C:\\Game\\.game\\worldsaves");
        gsm.states.push(new WorldState(gsm, world));
    }

    private void firstWorld(String path) {
        currentState = "first";
        File file = new File(path + "\\new.world");
        System.out.println(className + " Loading First World Creator");
        System.out.println(className + " Deleting Temporary new.world File");
        if (file.delete()) {
            System.out.println(className + " File Successfully Deleted");
        } else {
            System.out.println(className + " Failed to Delete File");
        }
        first = false;
    }

    private void newWorld(String path) {
        currentState = "create";
    }

    private void drawFirst(Graphics g) {
        g.setColor(MenuState.backgroundColor);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Ariel", Font.PLAIN, 24));
        g.drawString("Create Your First World", 100, GamePanel.HEIGHT / 2);
        g.drawString(totalName, 100, GamePanel.HEIGHT / 2 + 50);
    }

    private void drawCreate(Graphics g) {

    }

    private void drawList(Graphics g) {
        for (int i = 0; i < worlds.size(); i++) {
            World world = worlds.elementAt(i);
            String name = world.getWorldName();
        }
    }

    private int $aj = 0;

    public void init() {
        loadWorlds(path);
    }

    public void tick() {
        if (first && $aj == 1) {
            firstWorld("C:\\Game\\.game\\worldsaves");
        } else if (loadFirst) {
            World world = new World(totalName, "C:\\Game\\.game\\worldsaves");
            gsm.states.push(new WorldState(gsm, world.getAbsolutePath()));
        }
        $aj += 1;
    }

    public void draw(Graphics g) {
        if (currentState.equals("first")) {
            drawFirst(g);
        }
        if (currentState.equals("create")) {
            drawCreate(g);
        }
        if (currentState.equals("list")) {
            drawList(g);
        }
    }

    public void keyPressed(int k) {
        if (currentState.equals("first")) {
            firstKey(k);
        }
    }

    public void keyReleased(int k) {

    }

    private void firstKey(int k) {

        if (!(k == KeyEvent.VK_ENTER)) {
            String l = KeyEvent.getKeyText(k);
            if (l.equals("Space")) {
                l = " ";
            }
            if (l.length() == 1 || l.equals(" ")) {
                totalName = totalName + l;
                System.out.println(className + " " + totalName);
            }
        } else {
            totalName = totalName.toLowerCase();
            finishWorldCreation("C:\\Game\\.game\\worldsaves");
            load = true;
        }
    }

    private void finishWorldCreation(String path) {
        World newWorld = new World(totalName, path);
        newWorld.create();
        newWorld.colours();
        loadFirst = true;
    }
}
