package de.cg.cgge.game;

import de.cg.cgge.physics.Physics;
import de.cg.cgge.physics.shapes.CollisionShape;

import java.util.ArrayList;

public class PhysicalGameObject extends GameObject{

    protected CollisionShape collisionShape;
    private ArrayList<Physics> physicsComponents = new ArrayList<>(0);

    /**
     * Creates a GameObject, important for logic and drawing
     *
     * @param room calls a getObjectManager().addObject(this) on the room
     */
    public PhysicalGameObject(Room room, CollisionShape shape) {
        super(room);
        this.collisionShape = shape;
    }

    public ArrayList<Physics> getPhysicsComponents() {
        return this.physicsComponents;
    }

    /**
     * @param p A de.cg.cgge.physics.Physics instance.
     */
    public void addPhysicsComponent(Physics p) {
        this.physicsComponents.add(p);
    }

    /**
     * Call an update() method on all physics of the object
     */
    public void updatePhysics() {
        for (Physics p : physicsComponents) {
            p.update();
        }
    }
}
