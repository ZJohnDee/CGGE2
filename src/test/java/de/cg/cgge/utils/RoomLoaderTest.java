package de.cg.cgge.utils;

import de.cg.cgge.files.GameFile;
import de.cg.cgge.game.GameInstance;
import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class RoomLoaderTest {

    private GameInstance game;

    @Before
    public void init() {
        game = new GameInstance("src//test//demoConfig.data");
    }

    @Test
    public void testRoomLoad() {
        RoomLoader rl = new RoomLoader(game);

        try {
            GameFile rf = new GameFile("src//test//demoRoom.room");
            rf.loadToMemory();

            Room r = rl.loadRoom(rf);

            Assert.assertNotNull(r);

            game.changeRoomSafely(r);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
