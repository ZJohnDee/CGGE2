package de.cg.cgge.ctrl;

import de.cg.cgge.game.Camera;
import de.cg.cgge.game.GameInstance;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CameraRendererTest {

    private GameInstance game;

    @Before
    public void init() {
        game = new GameInstance("src//test//demoConfig.data");
    }

    @Test
    public void testInView() {
        Camera cam = game.getRoom().getCamera();

        boolean test = cam.isInView(game.getWidth()-25, game.getHeight()-25, 50, 50);

        Assert.assertEquals(true, test);
    }

    @Test
    public void testInViewZoom() {
        Camera cam = game.getRoom().getCamera();
        cam.setZoom(2.0f);

        int x = game.getWidth()/2-25;
        int y = game.getHeight()/2-25;
        int w = 50;
        int h = 50;

        boolean test = cam.isInView(x,y,w,h);

        Assert.assertEquals(true, test);
    }


}
