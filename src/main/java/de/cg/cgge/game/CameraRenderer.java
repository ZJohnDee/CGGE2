package de.cg.cgge.game;

import java.awt.*;

import de.cg.cgge.gui.AnimatedSprite;
import de.cg.cgge.gui.Sprite;

public class CameraRenderer {

    private Graphics g; 

    private Camera cam;

    /**
     * Allows the use of graphics methods adjusted to the cameras position and zoom.
     * Objects also will not be drawn, when they are out of view of the camera
     * @param g GraphicsContext of the draw instance
     * @param cam The camera, that shall be considered
     */
    public CameraRenderer(Graphics g, Camera cam) {
        this.g = g; 
        this.cam = cam; 
    }

    /**
     * Fills a rectangle
     * @param x x-Position of the rectangle
     * @param y y-Position of the rectangle
     * @param w Width of the rectangle
     * @param h Height of the rectangle
     */
    public void fillRect(int x, int y, int w, int h) {

        int nx = (int) (x-cam.getX()*cam.getZoom());
        int ny = (int) (y-cam.getY()*cam.getZoom());

        if (cam.isInView(x, y, w, h))
            g.fillRect(nx, ny, (int) (w*cam.getZoom()), (int) (h*cam.getZoom()));
    }

    /**
     * Draws the outlines of a rectangle
     * @param x x-Position of the rectangle
     * @param y y-Position of the rectangle
     * @param w Width of the rectangle
     * @param h Height of the rectangle
     */
    public void drawRect(int x, int y, int w, int h) {

        int nx = (int) (x-cam.getX()*cam.getZoom());
        int ny = (int) (y-cam.getY()*cam.getZoom());

        if (cam.isInView(x, y, w, h)) 
            g.drawRect(nx, ny, (int) (w*cam.getZoom()), (int) (h*cam.getZoom()));
    }

    /**
     * Draws an given image
     * @param img The already loaded image. No loading is done at this point
     * @param x The x position
     * @param y The y position
     */
    public void drawImage(Image img, int x, int y) {
        if (cam.getZoom() != 1) {
            img = img.getScaledInstance((int) (img.getWidth(null)*cam.getZoom()), (int) (img.getHeight(null)*cam.getZoom()), Image.SCALE_REPLICATE);
        }

        if (cam.isInView(x, y, img.getWidth(null), img.getHeight(null))) 
            g.drawImage(img, (int) (x-cam.getX()), (int) (y-cam.getY()), null);
    }

    /**
     * Draws a string at a given position
     * @param str The string to be drawn
     * @param x The x-Position
     * @param y The y-Position
     */
    public void drawString(String str, int x, int y) {
        int height = (int) g.getFontMetrics().getLineMetrics(str, g).getHeight();
        if (cam.isInView(x, y, 1080, height)) {
            g.drawString(str, x, y);
        }
    }

    /**
     * 
     * @param sprite The already loaded sprite
     * @param x The x-Position of the sprite
     * @param y The y-Position of the sprite
     */
    public void drawSprite(Sprite sprite, int x, int y) {
        int nx = (int) (x-cam.getX());
        int ny = (int) (y-cam.getY());

        if (cam.isInView(nx, ny, sprite.getWidth(), sprite.getHeight()))
            sprite.draw(nx, ny, g);
    }

    /**
     * 
     * @param sprite The already loaded de.cg.cgge.gui.AnimatedSprite
     * @param x The x-Position, which is adjusted and then parsed to the AnimatedSprite.draw(x,y,g) method 
     * @param y The y-Position, which is adjusted and then parsed to the AnimatedSprite.draw(x,y,g) method
     */
    public void drawAnimatedSprite(AnimatedSprite sprite, int x, int y) {
        int nx = (int) (x-cam.getX());
        int ny = (int) (y-cam.getY());

        if (cam.isInView(nx, ny, sprite.getWidth(), sprite.getHeight()))
            sprite.draw(nx, ny, g);
    }

    /**
     * @param x1 The first x-Position
     * @param y1 The first y-Position
     * @param x2 The second x-Position
     * @param y2 The second y-Position
     */
    public void drawLine(int x1, int y1, int x2, int y2) {
        int w = x2-x1; 
        int h = y2-y2; 

        if (cam.isInView(w, h, w, h)) {
            g.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * Adjusts the x value based on the camera's x-Position
     * @param x The x-Value to be adjusted
     * @return Returns an adjusted value
     */
    public int getAdjustedX(int x) {
        return x-cam.getX(); 
    }

    /**
     * Adjusts the y value based on the camera's y-Position
     * @param y The y-Value to be adjusted
     * @return Returns an adjusted value
     */
    public int getAdjustedY(int y) {
        return y-cam.getY();
    }


    

}