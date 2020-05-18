package de.cg.cgge.ctrl;

import de.cg.cgge.game.GameInstance;


public class CGGE2 {

    private static final String version = "2.0";
    private static final int compatibilityLayer = 1;

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("CGGE2 Game Engine");
            System.out.println("Current Version: " + version);
        }

        if (args.length == 1) {
            if (args[0].equals("test")) {
                System.out.println("Test Launched");
                new GameInstance();
            }

        }
    }

    public static String getVersion() {
        return version;
    }

    public static int getCompatibilityLayer() {
        return compatibilityLayer;
    }
}
