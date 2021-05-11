package de.cg.cgge.physics;

import de.cg.cgge.game.GameInstance;
import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.PhysicalGameObject;

public abstract class Physics {

    protected PhysicalGameObject obj;

    public Physics(PhysicalGameObject obj) {
        this.obj = obj; 
    }

    public abstract void update();

    /**
     * Returns an adjusted delta value. 
     * Recommended for movement and physics, as it would make shure, that all movements would happen at an equal speed, no matter how bad the game runs
     * If the frame took exactly the right time, it returns 1.0
     * If it was slower, the return value is higher than 1.0
     * If it was faster for some reason, the return value is lower than 1.0
     * WARNING: When used with collisions, it may skip objects.
     * @param game The GameInstance, it is running on
     * @return Adjusted delta time
     */
    public static float adjustedDeltaTime(GameInstance game) {
        return ((float)game.getDrawer().getRoom().getClock().adjustedDeltaTime);
    }

    /**
     * Returns the delta time of the last frame.
     * The time difference between each frame
     * @param game The GameInstance, it is running on
     * @return Delta Time
     */
    public static float deltaTime(GameInstance game) {
        return ((float)game.getDrawer().getRoom().getClock().deltaTime);
    }
    
}