package de.cg.cgge.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.cg.cgge.game.GameObject;
import de.cg.cgge.gui.Drawer;

public class KeyManager implements KeyListener {

    private Drawer drawer;

    private boolean[] keys = new boolean[526];

    private Map<String, Input> inputs = new HashMap<>();

    public KeyManager(Drawer drawer) {
        this.drawer = drawer;

        for (int i = 0; i < keys.length; i++) {
            keys[i] = false;
        }
    }

    public void addInput(String name, Input input) {
        inputs.put(name, input);
    }

    public boolean checkInput(String name) {
        if (!inputs.containsKey(name)) return false;

        Input input = inputs.get(name);
        int[] keyCodes = input.getKeys();
        switch (input.getType()) {
            case KEY_PRESSED:
                for (int key : keyCodes) {
                    if (keys[key]) return true;   //Return true as soon as any key is pressed
                }
                break;


            case KEY_RELEASED:
                for (int key : keyCodes) {
                    if (keys[key]) return false;    //Return true as soon as a key is pressed
                }
                break;


            case KEY_JUST_PRESSED:
                for (int key : keyCodes) {
                    if (keys[key]) {
                        keys[key] = false;          //Set the key to false after the check
                        return true;
                    }
                }
                break;

            case ALL_KEYS_PRESSED:
                for (int key : keyCodes) {
                    if (!keys[key]) return false;   //Return false as soon as a key is not pressed
                }
                break;
        }
        return false;
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

    /**
     * Checks, whether a key was pressed or not.
     * @param keyCode The key code of the key to be checked
     * @return Returns the boolean value of the key
     */
    public boolean checkKey(int keyCode) {
        return keys[keyCode];
    }

    /**
     * Checks, whether a key was pressed or not.
     * When it returns true, the key is then 'unpressed' and is going to return false until the key is pressed again
     * Useful for e.g. inventory opening
     * @param keyCode The key code of the key to be checked
     * @return Returns the boolean value of the key
     */
    public boolean fetchKey(int keyCode) {
        boolean val = keys[keyCode];
        if (!val) return false; //Returning false, if the key is not pressed
        keys[keyCode] = false; //If key is pressed, then set the key globally to false
        return true; //Return true as the key was originally pressed
    }

}