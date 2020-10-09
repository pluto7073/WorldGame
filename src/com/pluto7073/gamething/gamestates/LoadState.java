package com.pluto7073.gamething.gamestates;

import com.pluto7073.gamething.GamePanel;
import com.pluto7073.gamething.Main;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class LoadState extends GameState {

    private double loadProgress = 0;
    public String path;
    private boolean newFile = false;
    private Color backgroundColour = new Color(0, 0, 0);
    private JarFile jar;
    SimpleDateFormat format = new SimpleDateFormat("HH.mm.ss");
    Date date = new Date(System.currentTimeMillis());
    String time = format.format(date);
    public static String logfilePath;
    public static String getLogfilePath;

    public LoadState(GameStateManager gsm) {
        super(gsm);
        System.out.println("[LoadState]: Loading Game...");
    }

    public void init() {

    }

    private void extractJar(String path) {
        String assets = "C:\\Game\\assets";
        File fi = new File(assets);
        if (fi.mkdirs()){}
        String images = assets + "\\images";
        if (fi.mkdirs()){}
        path = path + "\\.extractedGame";
        fi = new File(path);
        if (fi.mkdirs()) {

        } else {
            return;
        }
        try {
            JarFile jar = new JarFile("C:\\Game\\versions\\v1.0\\Game.jar");
            Enumeration enumEntries = jar.entries();
            while (enumEntries.hasMoreElements()) {
                JarEntry file = (JarEntry) enumEntries.nextElement();
                File f = new File(path + File.separator + file.getName());
                if (file.isDirectory()) {
                    f.mkdirs();
                    continue;
                }
                InputStream is = jar.getInputStream(file);
                FileOutputStream fos = new FileOutputStream(f);
                while (is.available() > 0) {
                    fos.write(is.read());
                }
                fos.close();
                is.close();
            }
            jar.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(className + " Moving Extracted Files to New Folder");
        Path temp = null;
        try {
            temp = Files.move
                    (Paths.get(path + "\\files\\images\\icon.png"),
                            Paths.get(images + "\\icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (temp != null) {
            System.out.println(className + " Images Directory Successfully Moved to Assets");
        } else {
            System.out.println(className + " Failed to Move Directory");
        }
    }

    private void newSaveDirectory(String path) {
        path = path + "\\worldsaves";
        File file = new File(path);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean c = file.mkdirs();
        if (c) {
            System.out.print(className + " Saves Directory Created at Position ");
            System.out.println(file.getAbsolutePath());
        } else {
            System.out.println(className + " Directory Already Exists");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            path = path + "\\new.world";
            File newWorld = new File(path);
            FileWriter writer = new FileWriter(path);
            writer.write(
                    "//This File Is Temporary Until You\n" +
                            "//Create Your First World\n" +
                            "//Do Not Delete Or You Will Not\n" +
                            "//Be Able to Create a World"
            );
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String newGameDirectory() {
        JFileChooser f = new JFileChooser();
        String path = "";
        f.setName("Choose a new Game Directory or Hit Cancel to Use the Default");
        f.setDialogTitle("Choose a Game Directory or Hit Cancel for Default");
        f.setCurrentDirectory(new File("C:\\Game"));
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = f.showOpenDialog(Main.f);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            path = f.getSelectedFile().toString();
        } else {
            path = "C:\\Game";
        }
        System.out.print(className + " Creating new Directory at: ");
        System.out.println(path);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        extractJar(path);
        path = path + "\\.game";
        System.out.print(className + " Creating File Directory ");
        System.out.println(path);
        File file = new File(path);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean c = file.mkdirs();
        if (c) {
            System.out.print(className + " File Directory Succesfully Created at location ");
            System.out.println(file.getAbsolutePath());
        } else {
            System.out.println(className + " Directory Already Exists");
        }
        String path1 = path + "\\logs";
        File file1 = new File(path1);
        if (file1.mkdirs()) {}
        return path;
    }

    private void newSettingsFile(String path) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(className + " Creating New Settings File...");
        path = path + "\\options";
        File file = new File(path);
        boolean c = file.mkdirs();
        if (c) {
            System.out.print(className + " New Options Directory Created At ");
            System.out.println(path);
        } else {
            System.out.println(className + " Directory Already Exists");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            path = path + "\\options.txt";
            System.out.println(className + " Creating New options.txt at path: " + path);
            File options = new File(path);
            FileWriter writer = new FileWriter(path);
            writer.write(
                            "PlayerColorR=255" +
                            "\nPlayerColorG=255" +
                            "\nPlayerColorB=255"
            );
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void newColorFile(String path) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(className + " Creating New Color File");
        path = path + "\\options\\colours.txt";
        File file = new File(path);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(
                    "ColorR=0" +
                            "\nColorG=0" +
                            "\nColorB=0"
            );
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadColor(String path) throws IOException {
        path = path + "\\options\\colours.txt";
        File file = new File(path);
        try {
            Scanner reader = new Scanner(file);
            int r = 0;
            int g = 0;
            int b = 0;
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if (data.contains("ColorR=")) {
                    data = data.replaceAll("ColorR=", "");
                    System.out.println(className + " Loading Color R: " + data);
                    r = Integer.parseInt(data);
                } else if (data.contains("ColorG=")) {
                    data = data.replaceAll("ColorG=", "");
                    System.out.println(className + " Loading Color G: " + data);
                    g = Integer.parseInt(data);
                } else if (data.contains("ColorB=")) {
                    data = data.replaceAll("ColorB=", "");
                    System.out.println(className + " Loading Color B: " + data);
                    b = Integer.parseInt(data);
                }
            }
            backgroundColour = new Color(r, g, b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void tick() {
        if (loadProgress < 100) {
            loadProgress += 1;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("[LoadState]: An Error Occurred When loading the Game");
                e.printStackTrace();
            }
            if (newFile) {
                if (loadProgress == 11) {
                    System.out.println(className + " Creating New Game Directory");
                    path = newGameDirectory();
                    try {
                        String path = "C:\\Game";
                        File file = new File(path);
                        boolean c = file.mkdirs();
                        if (c) {

                        }
                        path = path + "\\main.path";
                        file = new File(path);
                        FileWriter writer = new FileWriter(file);
                        writer.write(this.path);
                        writer.close();
                        file = new File("C:\\Game\\images");
                        if (file.mkdirs()) {
                            System.out.println(className + " Creating Image");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (loadProgress == 36) {
                    newSaveDirectory(path);
                } else if (loadProgress == 52) {
                    newSettingsFile(path);
                } else if (loadProgress == 74) {
                    newColorFile(path);
                }
            } else if (!newFile) {
                if (loadProgress == 56) {
                    File file = new File("C:\\Game\\main.path");
                    try {
                        Thread.sleep(1000);
                        Scanner reader = new Scanner(file);
                        while (reader.hasNextLine()) {
                            String data = reader.nextLine();
                            path = data;
                            File path = new File(data);
                            if (path.exists()) {
                                System.out.print(className + " Found Game Directory: ");
                                System.out.println(path);
                                try {
                                    loadColor(this.path);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                logfilePath = this.path + "\\logs\\" + time;
                                File file1 = new File(logfilePath);
                                if (file1.mkdirs()) {

                                }
                                getLogfilePath = logfilePath + "\\out.log";
                                file1 = new File(getLogfilePath);
                            } else {
                                System.out.println(className + " Could Not Find Path, Creating New Path...");
                                newFile = true;
                                loadProgress = 10;
                            }
                        }
                    } catch (FileNotFoundException | InterruptedException e) {
                        System.out.print(className + " Could not find path ");
                        System.out.println(path);
                        e.printStackTrace();
                    }
                }
            }
            if (loadProgress == 2) {
                File path = new File("C:\\Game");
                boolean c  = path.mkdirs();
                if (c) {

                }
                try {
                    File file = new File("C:\\Game\\main.path");
                    if (file.createNewFile()) {
                        newFile = true;
                    } else {
                        newFile = false;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                Main.f.setIconImage(new ImageIcon("C:\\Game\\.extractedGame\\files\\images\\icon.png").getImage());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("[LoadState]: An Error Occurred When loading the Game");
                e.printStackTrace();
            }
            gsm.states.push(new MenuState(gsm, path, backgroundColour));
        }
        Main.f.setIconImage(new ImageIcon("C:\\Game\\.extractedGame\\files\\images\\icon.png").getImage());
    }

    public void draw(Graphics g) {
        g.setColor(backgroundColour);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        g.setColor(Color.white);
        g.drawRect(195, GamePanel.HEIGHT / 2 - 5, 510, 74);
        g.fillRect(200, GamePanel.HEIGHT / 2, (int)loadProgress * 5, 64);
    }

    public void keyPressed(int k) {

    }

    public void keyReleased(int k) {

    }
}
