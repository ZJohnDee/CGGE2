package de.cg.cgge.physics.shapes;

public class CollisionBoxShape extends CollisionShape {

    private int width, height;

    public CollisionBoxShape(float x, float y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean isIntersecting(CollisionBoxShape other) {
        return (x+width >= other.getX()
                && y+height >= other.getY()
                && x <= other.getX()+ other.getWidth()
                && y <= other.getY()+ other.getHeight());
    }

    @Override
    protected boolean isIntersecting(CollisionCircleShape other) {
        return false;
    }

    @Override
    protected CollisionShape copy() {
        return new CollisionBoxShape(x,y,width,height);
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
