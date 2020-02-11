package de.cg.cgge.physics;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;

public class Collider {

    private int lastCollision = 0; 
    private Room room; 

    private GameObject tester; 

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
        Checks for box-collision with objects that are solid
        @param x x-Corner
        @param y y-Corner
        @param w Width of object to check
        @param h Height of object to check
        @return Whether there is a collision or not
    */
    public boolean checkSolidBoxCollision(float x, float y, int w, int h) {
        
        for (int i = 0; i<room.getObjectManager().getObjects().size(); i++) {
            GameObject obj = room.getObjectManager().getObjects().get(i); 

            if (obj.isSolid() && obj != tester && 
                    x < obj.getX() + obj.getWidth() &&
                    x + w > obj.getX() &&
                    y < obj.getY() + obj.getHeight() &&
                    y + h > obj.getY()) {
                
                lastCollision = i; 
                return true;
            }
            
        }

        return false; 
    }   
    
    /**
        Checks for box-collision with all objects
        @param x x-Corner
        @param y y-Corner
        @param w Width of object to check
        @param h Height of object to check
        @return Whether there is a collision or not
    */
    public boolean checkBoxCollision(float x, float y, int w, int h) {    

        for (int i = 0; i<room.getObjectManager().getObjects().size(); i++) {
            GameObject obj = room.getObjectManager().getObjects().get(i); 

            if (obj != tester && 
                    x < obj.getX() + obj.getWidth() &&
                    x + w > obj.getX() &&
                    y < obj.getY() + obj.getHeight() &&
                    y + h > obj.getY()) {
                
                lastCollision = i; 
                return true;
            }
            
        }

        
        return false; 
    }

    /**
        Checks for box-collision with not solid objects
        @param x x-Corner
        @param y y-Corner
        @param w Width of object to check
        @param h Height of object to check
        @return Whether there is a collision or not
    */
    public boolean checkUnsolidBoxCollision(int x, int y, int w, int h) {    

        for (int i = 0; i<room.getObjectManager().getObjects().size(); i++) {
            GameObject obj = room.getObjectManager().getObjects().get(i); 

            if (!(obj.isSolid()) && obj != tester && 
                    x < obj.getX() + obj.getWidth() &&
                    x + w > obj.getX() &&
                    y < obj.getY() + obj.getHeight() &&
                    y + h > obj.getY()) {
                
                lastCollision = i; 
                return true;
            }
            
        }

        
        return false; 
    }

    /**
     * 
     * @return Returns the object, the last collision occoured with
     */
    public GameObject getLastCollision() {
        return room.getObjectManager().getObjects().get(lastCollision);
    }



}