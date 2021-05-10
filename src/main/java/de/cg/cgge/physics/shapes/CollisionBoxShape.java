package de.cg.cgge.physics.shapes;

public class CollisionBoxShape extends CollisionShape {

    private int width, height;

    public CollisionBoxShape(int x, int y, int width, int height) {
        super(x, y);
    }

    @Override
    public boolean isIntersecting(CollisionBoxShape other) {
        return (x >= other.getX() && y >= other.getY() && x <= other.getX()+ other.getWidth() && y <= other.getY()+ other.getHeight());
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
