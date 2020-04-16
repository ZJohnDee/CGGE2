package de.cg.cgge.ctrl;


import de.cg.cgge.game.Camera;
import de.cg.cgge.game.CameraRenderer;
import de.cg.cgge.game.GameInstance;
import de.cg.cgge.game.GameObject;
import de.cg.cgge.gui.Sprite;
import de.cg.cgge.physics.Physics;

import java.awt.*;

public class App {

    public static String release = "pre-1.0-b6";

    public static boolean package_uiplus = false;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("CGGE2 Game Engine");
            System.out.println("Current Version: " + release);
        }

        if (args.length == 1) {
            if (args[0].equals("test")) {
                System.out.println("Test Launched");
                new GameInstance();
            }

            if (args[0].equals("packages")) {
                System.out.println("UIPlus: " + package_uiplus);
            }
        }
    }
}
