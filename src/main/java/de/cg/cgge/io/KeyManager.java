package de.cg.cgge.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.gui.Drawer;

public class KeyManager implements KeyListener {

    private Drawer drawer; 

    public KeyManager(Drawer drawer) {
        this.drawer = drawer; 
    }

    @Override
    public void keyTyped(KeyEvent e) {
        for (GameObject obj : drawer.getRoom().getObjectManager().getObjects()) {
            obj.keyTyped(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (GameObject obj : drawer.getRoom().getObjectManager().getObjects()) {
            obj.keyPressed(e);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (GameObject obj : drawer.getRoom().getObjectManager().getObjects()) {
            obj.keyReleased(e);
        }
    }

}