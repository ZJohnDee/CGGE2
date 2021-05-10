package de.cg.cgge.events;

import de.cg.cgge.game.Room;

public class RoomChangedEvent extends Event{

    private Room from, to;

    public RoomChangedEvent(Room from, Room to)
    {
        this.from = from;
        this.to = to;
    }

    public Room getFrom() {
        return from;
    }

    public Room getTo() {
        return to;
    }
}
