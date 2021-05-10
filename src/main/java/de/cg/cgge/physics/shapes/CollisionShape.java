package de.cg.cgge.physics.shapes;

public abstract class CollisionShape {

    protected int x,y;

    public CollisionShape(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public abstract boolean isIntersecting(CollisionBoxShape other);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
