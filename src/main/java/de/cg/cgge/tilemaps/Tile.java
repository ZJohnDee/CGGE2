package de.cg.cgge.tilemaps; 

import java.awt.*;
import java.awt.image.BufferedImage;

import de.cg.cgge.game.CameraRenderer;

public class Tile {

    private TileMap owner; 
    private int section; 
    private int tileSize; 
    private int scale; 
    private Image partImage; 

    public Tile(TileMap owner, int section, int tileSize, int scale) {
        this.owner = owner; 
        this.section = section; 
        this.tileSize = tileSize; 
        this.scale = scale; 

        //Get part image and store it to memory
        BufferedImage img = owner.getTileSet(); 

        int imgX = 0; 
        int imgY = 0; 

        int imgColumns = img.getWidth()/tileSize;
        int imgRows = img.getHeight()/tileSize;

        int counter = 0; 
        boolean tf = false;
        for (int i = 0; i<imgRows && !tf; i++) {
            for (int j = 0; j<imgColumns && !tf; j++) {
                if (counter == section) {
                    imgX = j*tileSize;
                    imgY = i*tileSize;
                    tf = true; 
                }
                counter++;
            }
        }

        partImage = img.getSubimage(imgX, imgY, tileSize, tileSize).getScaledInstance(tileSize*scale, tileSize*scale, Image.SCALE_REPLICATE);
    }

    public void draw(Graphics g, int x, int y) {
        
        CameraRenderer cr = new CameraRenderer(g, owner.getRoom().getCamera());

        cr.drawImage(partImage, tileSize*scale*x, tileSize*scale*y);
    }

    public int getSection() {
        return this.section;
    }



}