package de.cg.cgge.object;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class Sprite {
    /*
        Sprite 2.0

        Merged Animated and normal sprites

        Extended GameObject

        Added Sprite drawing optimization

        Changed variable names

        Added heavy documentation and fixed logic errors
     */

    //TODO: Add zoom

    //X, Y, and Rotation on the coordinate screen
    int x = 0, y = 0, rotation = 0;

    //Base dimensions of the sprite
    int height, width;

    //The X and Y coordinate on the sprite that the origin or anchor point is located
    //This is 0,0 or the top left corner by default
    int relativeX = 0, relativeY = 0, relativeRotation = 0;

    //Amount of sprite frames inside of your
    int flipbookCount = 1;

    //Each frame inside the flipbook image
    Image[] frameTextures = new Image[100];

    //Base flipbook image file
    BufferedImage image;

    //All animations for the sprite
    //This is sorted by Name : Animation
    //Used a HashMap because it allows for some really readable code in terms
    //of switching animations based on the state (Key actions, events, etc.)
    HashMap<String, Animation> animations = new HashMap<>();

    //The current tick within the current animations cycle
    //This has a max value of the sum of every frame of the current animation plus one
    int currentTick = 1;

    //The current animation shown of the player
    Animation currentAnimation;

    //The visibility of the player
    boolean visible = false;

    //If the last frame is at all different from this frame
    boolean changed = true;

    /**
     * @param texture Flipbook image
     */
    public Sprite(File texture) {
        System.out.println(texture.getAbsolutePath());
        try {
            this.image = ImageIO.read(texture);
            width = this.image.getWidth();
            initFlipbookTextures();
            currentAnimation = Animation.DEFAULT;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a Sprite from another sprite
     *
     * @param sprite
     */
    public Sprite(Sprite sprite) {
        this.image = sprite.image;
        this.width = sprite.width;
        this.flipbookCount = sprite.flipbookCount;
        initFlipbookTextures();
        currentAnimation = Animation.DEFAULT;
    }

    /**
     * Grabs the flipbook image and
     * cut it into flipbookCount amount
     * of equal pieces on the y axis
     * and save them to frameTextures
     */
    private void initFlipbookTextures() {
        //This could be very inaccurate if the developer didn't define a proper flipbook count
        //By default it's 1 so that should be fine
        this.height = this.image.getHeight() / this.flipbookCount;

        //Increases until the frameTextures array is filled
        for (int frame = 0; frame < flipbookCount; frame++) {
            //This grabs each vertical frame in an image and adds it to the frameTextures list
            frameTextures[frame] = this.image.getSubimage(0, frame * height, width, height);
        }
    }

    /**
     * Sets the location of the sprite
     *
     * @param x X coordinate from the top left corner being 0
     * @param y Y coordinate from the top left corner being 0
     */
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        changed = true;
    }

    /**
     * Sets the rotation of the sprite. This doesn't currently matter for now
     *
     * @param rotation
     */
    public void setRotation(int rotation) {
        this.rotation = rotation;
        changed = true;
    }

    /**
     * Add an animation to the list of possible animations
     *
     * @param name      Name of animation that you can reference later on
     * @param animation Animation object
     */
    public void addAnimation(String name, Animation animation) {
        animations.put(name, animation);
    }

    /**
     * Set the current Animation
     *
     * @param animation
     */
    public void showAnimation(Animation animation) {
        currentTick = 1;
        currentAnimation = animation;
        changed = true;
    }

    /**
     * Set the current Animation
     *
     * @param name Name of animation in registered animation list
     */
    public void showAnimation(String name) {
        currentTick = 1;
        currentAnimation = animations.get(name);
        changed = true;
    }

    /**
     * Allow the sprite to be rendered
     */
    public void show() {
        this.visible = true;
        this.changed = true;
    }

    /**
     * Disable the sprite from being rendered
     */
    public void hide() {
        this.visible = false;
    }

    /**
     * Set the X location of the sprite
     * <p>
     * This takes into consideration relativity and origin
     *
     * @param x X coordinate on screen
     */
    public void setX(int x) {
        this.x = x - relativeX;
        changed = true;
    }

    /**
     * Move a set number of pixels on the X axis
     * <p>
     * Use a negative value to move left
     *
     * @param x Pixels to move
     */
    public void moveX(int x) {
        this.x += x - relativeX;
        changed = true;
    }

    /**
     * Set the Y location of the sprite
     * <p>
     * This takes into consideration relativity and origin
     *
     * @param y Y coordinate on screen
     */
    public void setY(int y) {
        this.y = y - relativeY;
        changed = true;
    }

    /**
     * Move a set number of pixels on the Y axis
     * <p>
     * Use a negative value to move up
     *
     * @param y Pixels to move
     */
    public void moveY(int y) {
        this.y += y - relativeY;
        changed = true;
    }

    /**
     * Set the amount of frames inside your flipbook texture file
     *
     * @param flipbookCount Amount of frames
     */
    public void setFlipbookCount(int flipbookCount) {
        this.flipbookCount = flipbookCount;
        changed = true;
        initFlipbookTextures();
    }

    /**
     * Sets the relative origin X value
     *
     * @param relativeX X coordinate
     */
    public void setRelativeX(int relativeX) {
        this.relativeX = relativeX;
    }

    /**
     * Sets the relative origin Y value
     *
     * @param relativeY Y coordinate
     */
    public void setRelativeY(int relativeY) {
        this.relativeY = relativeY;
    }

    /**
     * Sets the relative origin rotation
     *
     * @param relativeRotation Rotation value
     */
    public void setRelativeRotation(int relativeRotation) {
        this.relativeRotation = relativeRotation;
    }

    /**
     * Sets the relative origin
     *
     * @param relativeX
     * @param relativeY
     */
    public void setRelativeLocation(int relativeX, int relativeY) {
        this.relativeX = relativeX;
        this.relativeY = relativeY;

        changed = true;
    }

    /**
     * Sets the relative origin and rotation
     *
     * @param relativeX
     * @param relativeY
     * @param relativeRotation
     */
    public void setRelativeLocation(int relativeX, int relativeY, int relativeRotation) {
        this.relativeX = relativeX;
        this.relativeY = relativeY;
        this.relativeRotation = relativeRotation;

        changed = true;
    }

    /**
     * Draws the current sprite to the window
     * <p>
     * This should be ran every tick
     *
     * @param g
     */
    public void draw(Graphics g) {

        //Only draw if the sprite is visible and there was a change from the last frame
        if (visible && changed) {
            //If there is 0 rotation, draw the image normally
            if (rotation + relativeRotation == 0) {
                int frame = currentAnimation.getFrameFromTick(currentTick);
                g.drawImage(frameTextures[frame], x - relativeX, y - relativeY, null);
            } else {
                //Adds rotation
                int frame = currentAnimation.getFrameFromTick(currentTick);
                AffineTransform at = AffineTransform.getTranslateInstance(x - relativeX, y - relativeY);
                at.rotate(Math.toRadians(rotation + relativeRotation), relativeX, relativeY);
                Graphics2D g2d = (Graphics2D) g;

                g2d.drawImage(frameTextures[frame], at, null);
            }
            changed = false;
        }
        tick();
    }

    /**
     * Increments the current animation tick and does some vital checks
     */
    private void tick() {
        currentTick++;

        //Checks if the tick is at the current animations last tick
        if (currentTick >= currentAnimation.totalTicks) {
            currentTick = 1;
            return;
        }

        //Checks if the last frame is the same as the current frame
        if (currentAnimation.getFrameFromTick(currentTick - 1) != currentAnimation.getFrameFromTick(currentTick))
            changed = true;
    }
}
