package de.cg.cgge.io;

import de.cg.cgge.game.GameInstance;

import java.awt.MouseInfo;
import java.awt.PointerInfo;

public class MouseHelper {
    
    GameInstance game;

    /**
     * Has basic mouse utilities
     * @param game The GameInstance, the mouse handler belongs to
     */
    public MouseHelper(GameInstance game) {
        this.game = game; 
    }

    /**
     * @return Returns the x-Postion of the mouse
     */
    public int getMouseX() {
        PointerInfo info = MouseInfo.getPointerInfo();
        return (int)info.getLocation().getX()-(int)game.getDrawer().getWindow().getBounds().getX();
    }

    /**
    * 
    * @return Returns the y-Postion of the mouse
    */
    public int getMouseY() {
        PointerInfo info = MouseInfo.getPointerInfo();
        return (int)info.getLocation().getY()-(int)game.getDrawer().getWindow().getBounds().getY();
    }


    /**
     * @param x Upper left corner of section
     * @param y Upper left corner of section
     * @param w Width of section
     * @param h Height of section
     * @return If mouse is in given section, it returns true
     */
    public boolean inMouseRange(int x, int y, int w, int h) {
        final int mx = getMouseX();
        final int my = getMouseY();

        return (mx > x && mx < x+w && my > y && my < y+h);
    }

}