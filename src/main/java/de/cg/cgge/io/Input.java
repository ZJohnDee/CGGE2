package de.cg.cgge.io;

public class Input {

    private int[] keys;
    private Type type;

    public Input(Type type, int... keys) {
        this.type = type;
        this.keys = keys;
    }

    public enum Type {
        KEY_PRESSED, KEY_RELEASED, KEY_JUST_PRESSED, ALL_KEYS_PRESSED
    }

    public int[] getKeys() {
        return keys;
    }

    public Type getType() {
        return type;
    }

}
