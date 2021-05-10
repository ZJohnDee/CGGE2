package de.cg.cgge.physics.shapes;

public abstract class CollisionShape {

    protected float x,y;

    public CollisionShape(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * This method checks whether this is intersecting with the given CollisionShape
     * @param other The collision shape that is to be checked for
     * @return Whether or not the intersection occurs.
     */
    public boolean isIntersecting(CollisionShape other)
    {
        if (other instanceof CollisionBoxShape) return isIntersecting((CollisionBoxShape) other);
        return false;
    }

    protected abstract boolean isIntersecting(CollisionBoxShape other);

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
