package de.cg.cgge.game;

import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;

import de.cg.cgge.physics.Physics;

public abstract class GameObject {

    protected boolean visible = true;
    protected boolean solid = true; 

    protected float x = 0, y = 0;

    protected int w = 0, h = 0;

    protected String name = "";

    protected long id = 0L;  

    protected Room room; 

    private ArrayList<Physics> physics = new ArrayList<>(0);
 

    public GameObject(Room room) {
        this.room = room; 
        room.getObjectManager().addObject(this);
    }

    /**
     * Called on object creation (The point, the object is added to the object list)
     */
    protected abstract void create();

    /**
     * Is called every tick, before all the step methods are called
     * Is here for game logic, that needs to be executed in the beginning of every tick
     */
    public abstract void preStep(); 

    /**
     * Is called every tick. Is here for game logic
     */
    public abstract void step();

    /**
     * Is drawn in the middle of every tick
     * Is drawn between tile map layers 2 and 3
     * @param g The Graphics element of the JPanel
     */
    public abstract void draw(Graphics g);
    
    /**
     * Is drawn at the end of the tick
     * @param g The Graphics element of the JPanel
     */
    public abstract void postDraw(Graphics g);

    /**
     * @param e Event thats called, when mouse is clicked
     */
    public abstract void mouseClicked(MouseEvent e);

    /**
     * @param e Event thats called, when mouse is pressed
     */
    public abstract void mousePressed(MouseEvent e); 

    /**
     * @param e Event thats called, when mouse is pressed
     */
    public abstract void mouseReleased(MouseEvent e); 

    /**
     * @param e Event thats called, when key is pressed
     */
    public abstract void keyPressed(KeyEvent e);

    /**
     * @param e Event thats called when key is released
     */
    public abstract void keyReleased(KeyEvent e); 

    /**
     * @param e Event thats called when key is typed
     */
    public abstract void keyTyped(KeyEvent e); 

    /**
     * Destroys the object and removes it from the object manager
     */
    public void destroy() {
        room.getObjectManager().removeObject(this);
    }

    public long getID() {
        return this.id;
    }

    public void setID(long id) {
        this.id = id; 
    }

    public boolean isVisible() {
        return visible; 
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setX(float x) {
        this.x = x; 
    }

    public float getX() {
        return this.x;
    }

    public void setY(float y) {
        this.y = y; 
    }

    public float getY() {
        return this.y; 
    }

    public void setWidth(int width) {
        this.w = width;
    }

    public int getWidth() {
        return this.w; 
    }

    public void setHeight(int height) {
        this.h = height; 
    }

    public int getHeight() {
        return this.h; 
    }

    public boolean isSolid() {
        return this.solid; 
    }

    public Room getRoom() {
        return this.room;
    }

    public ArrayList<Physics> getPhysics() {
        return this.physics;
    }


    /**
     * @param p A de.cg.cgge.physics.Physics instance.
     */
    public void addPhysics(Physics p) {
        this.physics.add(p);
    }

    /**
     * Call an update() method on all physics of the object
     */
    public void updatePhysics() {
        for (Physics p : physics) {
            p.update();
        } 
    }

    

} 