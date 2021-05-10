package de.cg.cgge.physics;

import de.cg.cgge.physics.shapes.CollisionBoxShape;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCollisionShapes {

    CollisionBoxShape b1;
    CollisionBoxShape b2;
    CollisionBoxShape b3;

    @Before
    public void init()
    {
        b1 = new CollisionBoxShape(20, 30, 100, 100);
        b2 = new CollisionBoxShape(30, 120, 10, 10);
        b3 = new CollisionBoxShape(500, 500, 10, 10);
    }

    @Test
    public void testCollidingBoxesDetected()
    {
        Assert.assertTrue(b1.isIntersecting(b2));
        Assert.assertTrue(b2.isIntersecting(b1));
    }

    @Test
    public void testNotCollidingBoxesNotDetected()
    {
        Assert.assertFalse(b1.isIntersecting(b3));
        Assert.assertFalse(b2.isIntersecting(b3));
        Assert.assertFalse(b3.isIntersecting(b1));
        Assert.assertFalse(b3.isIntersecting(b2));
    }

}
