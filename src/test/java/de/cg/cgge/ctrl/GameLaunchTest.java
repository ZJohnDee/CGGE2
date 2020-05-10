package de.cg.cgge.ctrl;

import de.cg.cgge.game.GameInstance;
import org.junit.Assert;
import org.junit.Test;

public class GameLaunchTest {

    @Test
    public void testLaunch() {
        GameInstance game = new GameInstance("src//test//demoConfig.data");
        Assert.assertNotNull(game);
    }

}
