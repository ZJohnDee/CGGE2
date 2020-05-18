package de.cg.cgge.objects;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;

public class TestFromRoom extends GameObject {

    public TestFromRoom(Room room, int x, int y, int w, int h) {
        super(room);

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public TestFromRoom(Room room, int x, int y) {
        super(room);

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
}
