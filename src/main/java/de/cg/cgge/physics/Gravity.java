package de.cg.cgge.physics;

import de.cg.cgge.game.GameObject;

public class Gravity extends Physics {

    private float force = 1.0f; 
    private final float beginForce;
    private float acceleration = 1.0f; 

    private Mover mover; 
    private Collider collider; 

    /**
     * Uses a mover to pull an object to the ground.
     * It takes acceleration into account and stops once the object hits something solid
     * @param obj The object to be pulled down
     * @param force The velocity at which the object is pulled down
     * @param mover The mover of the object, that HAS to be included to the object's physics
     */
    public Gravity(GameObject obj, float force, Mover mover) {
        super(obj);

        this.force = force; 
        this.beginForce = force; 

        this.mover = mover; 
        mover.setYacceleration(1.0f);
        this.collider = new Collider(obj.getRoom(), obj);
    }

    @Override
    public void update() {
        mover.setYspeed(mover.getYspeed()+force);

        force*=acceleration; 
        if (collider.checkSolidBoxCollision(obj.getX(), obj.getY()+1, obj.getWidth(), obj.getHeight())
        || mover.getYspeed() > 0) {
            force = beginForce; 
        }
    }

    public void setForce(Float force) {
        this.force = force; 
    }

    public float getForce() {
        return this.force; 
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration; 
    } 

    public float getAcceleration() {
        return this.acceleration; 
    }

    public void reset() {
        force = beginForce; 
    }

}