package de.cg.cgge.physics;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;

public abstract class Collider {

    protected int lastCollision = 0;
    protected Room room;

    protected GameObject tester;

    /**
     * Creates a collider, which is necessary for Collision detection
     * It is NOT of instance de.cg.cgge.physics.Physics
     * @param room The current game room
     * @param tester The object, that tests for collisions
     */
    public Collider(Room room, GameObject tester) {
        this.room = room;
        this.tester = tester; 
    } 

    /**
        Checks for collision with objects that are solid
        @param x x-Corner
        @param y y-Corner
        @param w Width of object to check
        @param h Height of object to check
        @return Whether there is a collision or not
    */
    abstract boolean checkSolidCollision(float x, float y, int w, int h);
    
    /**
        Checks for box-collision with all objects
        @param x x-Corner
        @param y y-Corner
        @param w Width of object to check
        @param h Height of object to check
        @return Whether there is a collision or not
    */
    abstract boolean checkCollision(float x, float y, int w, int h);

    /**
        Checks for collisions with not solid objects
        @param x x-Corner
        @param y y-Corner
        @param w Width of object to check
        @param h Height of object to check
        @return Whether there is a collision or not
    */
    abstract boolean checkUnsolidCollision(int x, int y, int w, int h);

    /**
     * 
     * @return Returns the object, the last collision occoured with
     */
    public GameObject getLastCollision() {
        return room.getObjectManager().getObjects().get(lastCollision);
    }



}