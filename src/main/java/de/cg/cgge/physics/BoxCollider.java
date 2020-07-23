package de.cg.cgge.physics;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;

public class BoxCollider extends Collider {

    /**
     * Creates a collider, which is necessary for Collision detection
     * It is NOT of instance de.cg.cgge.physics.Physics
     *
     * @param room   The current game room
     * @param tester The object, that tests for collisions
     */
    public BoxCollider(Room room, GameObject tester) {
        super(room, tester);
    }

    @Override
    boolean checkSolidCollision(float x, float y, int w, int h) {

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

    @Override
    boolean checkCollision(float x, float y, int w, int h) {
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

    @Override
    boolean checkUnsolidCollision(int x, int y, int w, int h) {
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
}
