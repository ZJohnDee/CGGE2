package de.cg.cgge.game;

public class PhysicalGameObject extends GameObject{



    /**
     * Creates a GameObject, important for logic and drawing
     *
     * @param room calls a getObjectManager().addObject(this) on the room
     */
    public PhysicalGameObject(Room room) {
        super(room);
    }
}
