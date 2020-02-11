package de.cg.cgge.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.gui.Drawer;

public class KeyManager implements KeyListener {

    private Drawer drawer;

    private boolean[] keys = new boolean[526];

    public KeyManager(Drawer drawer) {
        this.drawer = drawer;

        for (int i = 0; i < keys.length; i++) {
            keys[i] = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        for (GameObject obj : drawer.getRoom().getObjectManager().getObjects()) {
            obj.keyTyped(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

}