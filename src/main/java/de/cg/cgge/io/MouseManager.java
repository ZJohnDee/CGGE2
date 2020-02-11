package de.cg.cgge.io;

import java.awt.event.*;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.gui.Drawer;

public class MouseManager implements MouseListener {

    private Drawer drawer; 

    public MouseManager(Drawer drawer) {
        this.drawer = drawer; 
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (GameObject obj : drawer.getRoom().getObjectManager().getObjects()) {
            obj.mouseClicked(e);
        } 

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (GameObject obj : drawer.getRoom().getObjectManager().getObjects()) {
            obj.mousePressed(e);
        } 

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (GameObject obj : drawer.getRoom().getObjectManager().getObjects()) {
            obj.mouseReleased(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

}