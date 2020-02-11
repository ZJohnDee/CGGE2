package de.cg.cgge.gui;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.geom.AffineTransform;

import javax.imageio.ImageIO;

public class AnimatedSprite {

    int width, height, rotation, length;
    int centerX = 0, centerY = 0;
    int currentFrame = 0;

    int animationInterval = 0;
    int animationRangeA = 0;
    int animationRangeB = 0;

    private String[] paths;
    private BufferedImage[] imgs;

    private final Thread thread;

    /**
     * This creates an AnimatedSprite.
     * An AnimatedSprite cycles through all the images given at a specific rate to simualte motion
     * @param height The height, the image should be drawn at
     * @param width The widht, the image should be drawn at
     * @param rotation The rotation of the image
     * @param paths The paths of all the images; They NOT are loaded into memory at that point
     */
    public AnimatedSprite(int height, int width, int rotation, String... paths) {
        this.width = width;
        this.height = height;
        this.rotation = rotation;
        
        this.paths = new String[paths.length];

        for (int i = 0; i < paths.length; i++) {
            this.paths[i] = paths[i];
        }

        length = paths.length;

        imgs = new BufferedImage[length];

        this.thread = initThread();
        
        System.out.println("Constructed");
    }

    /**
     * Does a copy of an already existing animated sprite
     * @param as An already existing animated sprite
     */
    public AnimatedSprite(AnimatedSprite as) {
        this.imgs = as.getImages(); 
        this.height = as.getHeight(); 
        this.width = as.getWidth(); 
        this.rotation = as.getRotation();

        this.thread = initThread(); 
    }

    private Thread initThread() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                int start = getAnimationRangeA(); 
                int end = getAnimationRangeB();
                int currentFrame = start; 

                while (true) {
                    
                    //Reset animation
                    if (currentFrame > end) {
                       currentFrame = start;  
                    }


                    setCurrentFrame(currentFrame);

                    currentFrame++; 


                    try {
                        Thread.sleep(getAnimationInterval());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return thread; 
    }

    /**
     * Loads all the images into memory
     */
    public void load() {
        for (int i = 0; i<length; i++) {
            String path = paths[i];

            try {
                imgs[i] = ImageIO.read(new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Draws the sprite at given position
     * @param x x-Position
     * @param y y-Position
     * @param g Graphics instance
     */
    public void draw(int x, int y, Graphics g) {
        if (rotation == 0) {
            g.drawImage((Image) imgs[currentFrame], x, y, width, height, null);
        } else {
            AffineTransform at = AffineTransform.getTranslateInstance(x, y); 
            at.rotate(Math.toRadians(rotation), centerX, centerY);
            Graphics2D g2d = (Graphics2D) g; 

            g2d.drawImage(imgs[currentFrame], at, null); 
        }
    }

    /**
     * Starts the animation in a certain range of images
     * @param start Index of the first image
     * @param end Index of the last image
     * @param interval The time between all the images
     */
    public void startAnimation(int start, int end, int interval) {
        animationInterval = interval;
        animationRangeA = start; 
        animationRangeB = end; 

        thread.start();
    }

    /**
     * Stops the animation
     */
    public void stopAnimation() {
        if (thread.isAlive()) {
            thread.stop();
        }
    }  

    public void setWidth(int width) {
        this.width = width; 
    }

    public int getWidth() {
        return this.width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return this.height; 
    }

    /**
     * Sets the rotation of the image
     * The image is rotated around the gvien center
     * @param rotation The rotation in degrees
     */
    public void setRotation(int rotation) {
        this.rotation = rotation; 
    }

    public int getRotation() {
        return this.rotation; 
    }

    /**
     * Sets the current image
     * @param frame Index of the image
     */
    public void setCurrentFrame(int frame) {
        this.currentFrame = frame; 
    }

    /**
     * @return Returns the index of the currently displayed frame
    */
    public int getCurrentFrame() {
        return this.currentFrame;
    } 

    /**
     * Sets the center x-Value of the image
     * @param cx The x-Value
     */
    public void setCenterX(int cx) {
        this.centerX = cx; 
    }

    public int getCenterX() {
        return this.centerX; 
    }

    /**
     * Sets the center y-Value of the image
     * @param cy The y-Value
     */
    public void setCenterY(int cy) {
        this.centerY = cy; 
    }

    public int getCenterY() {
        return this.centerY; 
    }

    private int getAnimationInterval() {
        return this.animationInterval;
    }

    private int getAnimationRangeA() {
        return this.animationRangeA;
    }

    private int getAnimationRangeB() {
        return this.animationRangeB;
    }

    public BufferedImage[] getImages() {
        return imgs; 
    }







}