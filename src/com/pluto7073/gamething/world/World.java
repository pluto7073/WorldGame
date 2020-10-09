package com.pluto7073.gamething.world;

import com.pluto7073.gamething.vectors.three.Vector3i;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

@NotNull public class World {

    private String name;
    private String savePath;
    private Color[] ground = {
            new Color(156, 114, 2),
            new Color(156, 71, 2),
            new Color(71, 32, 0),
            new Color(71, 44, 0)
    };
    private Color[] sky = {
            new Color(139, 196, 252),
            new Color(47, 142, 235),
            new Color(47, 85, 235),
            new Color(23, 14, 69)
    };

    public World(String name, String savePath) {
        this.name = name;
        this.savePath = savePath + "\\" + this.name;
    }

    public void create() {
        File file = new File(savePath);
        file.mkdirs();
        String lw = savePath + "\\load.world";
        File lwf = new File(lw);
        try {
            lwf.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter writer;
        try {
            writer = new FileWriter(lwf, true);
            writer.append("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n");
            writer.append("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n");
            for (int i = 0; i < 20; i++) {
                String a = "";
                for (int j = 0; j < 36; j++) {
                    Random random = new Random();
                    int x = random.nextInt((i + 1) * 5);
                    if (x == 0) {
                        a = a + "a";
                    } else {
                        a = a + "g";
                    }
                }
                a = a + "\n";
                writer.append(a);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void colours() {
        String path = savePath + "\\colours";
        File file = new File(path);
        file.mkdirs();
        Random rd = new Random();
        Color ground = this.ground[rd.nextInt(this.ground.length)];
        String gd = path + "\\ground.col";
        File gdf = new File(gd);
        try {
            gdf.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter writer;
        try {
            writer = new FileWriter(gdf);
            String c = "" + ground.getRed() + "\n" + ground.getGreen() + "\n" + ground.getBlue();
            writer.write(c);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Color sky = this.sky[rd.nextInt(this.sky.length)];
        String sy = path + "\\sky.col";
        File syf = new File(sy);
        try {
            syf.createNewFile();
            writer = new FileWriter(syf);
            String c = "" + sky.getRed() + "\n" + sky.getGreen() + "\n" + sky.getBlue();
            writer.write(c);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getWorldName() {
        return name;
    }

    public String getSavePath() {
        return savePath;
    }

    public String getAbsolutePath() {
        return savePath + "\\" + name;
    }

    public void load() {

    }

}
