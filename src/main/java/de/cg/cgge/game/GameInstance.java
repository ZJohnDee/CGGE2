package de.cg.cgge.game;

import java.io.IOException;

import de.cg.cgge.files.FileContents;
import de.cg.cgge.files.GameFile;
import de.cg.cgge.gui.Drawer;

public class GameInstance {

    private String title = "GAME";
    private String configFile;

    private int framerate = 60;
    private int width = 1280;
    private int height = 720;

    private Drawer drawer;

    /**
     * Basic constructor to setup the game
     * Creates own drawer instance and launches the window
     */
    public GameInstance() {
        this(""); 
    }

    /**
     * Constructor to setup the game, with additional config files
     * Creates own drawer instance and launches the window
     * @param config The config; It's loaded as a GameFile
     */
    public GameInstance(String config) {
        configFile = config;  

        //Load config
        if (!config.equals("")) {
            try {
                GameFile gf = new GameFile(configFile);
                gf.loadToMemory(); 

                FileContents fc = gf.getContents();

                //Assign data from file to variable
                if (fc.getFromKeyword("title") != null)
                    title = fc.getFromKeyword("title");
                if (fc.getFromKeyword("width") != null)
                    width = Integer.parseInt(fc.getFromKeyword("width"));
                if (fc.getFromKeyword("height") != null)
                    height = Integer.parseInt(fc.getFromKeyword("height"));
                if (fc.getFromKeyword("framerate") != null)
                    framerate = Integer.parseInt(fc.getFromKeyword("framerate"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Init game instance
        drawer = new Drawer(this);
        drawer.initWindow();
        drawer.getRoom().getClock().start();

    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * 
     * @return The target framerate; It does not return the actual framerate
     */
    public int getFramerate() {
        return framerate;
    }
    
    public String getTitle() {
        return title;
    }

    public Drawer getDrawer() {
        return drawer;
    }

    /**
     * 
     * @return Shortcut to gameInstance.getDrawer().getRoom();
     */
    public Room getRoom() {
        return drawer.getRoom();
    }


    





}