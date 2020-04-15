package de.cg.cgge.gui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite  {

    private int height = 0, width = 0, rotation = 0, centerX = 0, centerY = 0, baseRotation = 0;
    private BufferedImage img = null;

    /**
     * Creates and loads a static sprite
     * @param path The path of the image
     * @param h Height of the image
     * @param w Width of the image
     * @param r Rotation of the image in degrees
     */
    public Sprite(String path, int h, int w, int r) {
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.height = h; this.width = w; this.rotation = r;
    }

    /**
     * Copies an already existing sprite
     * @param sprite The already existing sprite
     */
    public Sprite(Sprite sprite) {
        this.img = sprite.getImage(); 
        this.height = sprite.getHeight(); 
        this.width = sprite.getWidth(); 
        this.baseRotation = sprite.getBaseRotation(); 
        this.rotation = sprite.getRotation();
    }

    /**
     * Resets the dimensions of the sprite
     * @param width The height of the sprite
     * @param height The width of the sprite
     */
    public void setDimensions(int width, int height) {
        this.height = height; 
        this.width = width; 
    }

    /**
     * 
     * @param rotation Rotation in degrees
     */
    public void setRotation(int rotation) {
        this.rotation = rotation; 
    }

    /**
     * Sets the center, around which the sprite is rotated at
     * @param centerX Center x-Value
     * @param centerY Center y-Value
     */
    public void setCenter(int centerX, int centerY) {
        this.centerX = centerX; this.centerY = centerY;
    }

    /**
     * Draws the sprite at a given position
     * @param x The x-Position
     * @param y The y-Postion
     * @param g The graphics instance
     */
    public void draw(int x, int y, float zoom,  Graphics g) {
        if (rotation == 0) {
            g.drawImage((Image)img, x, y, (int)(width*zoom), (int)(height*zoom), null);
        } else {
            AffineTransform at = AffineTransform.getTranslateInstance(x, y); 
            at.rotate(Math.toRadians(rotation+baseRotation), centerX, centerY);
            at.setToScale(zoom, zoom);
            Graphics2D g2d = (Graphics2D) g;

            g2d.drawImage(img, at, null);
        }
    }

    public void draw(int x, int y, Graphics g) {
        draw(x,y,1f, g);
    }

    public int getRotation() {
        return this.rotation;
    }

    public int getBaseRotation() {
        return this.baseRotation;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    /**
     * 
     * @return Returns the buffered image
     */
    public BufferedImage getImage() {
        return this.img;
    }

}