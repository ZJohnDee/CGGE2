package de.cg.cgge.ctrl;


import de.cg.cgge.game.Camera;
import de.cg.cgge.game.CameraRenderer;
import de.cg.cgge.game.GameInstance;
import de.cg.cgge.game.GameObject;
import de.cg.cgge.gui.Sprite;
import de.cg.cgge.physics.Physics;

import java.awt.*;

public class App {

    public static Sprite sprite = new Sprite("rsc//player1.png", 50, 50, 0);

    public static void main(String[] args) {
        GameInstance game = new GameInstance();

        GameObject obj = new GameObject(game.getRoom()) {

            @Override
            public void create() {
                this.x = 20;
                this.y = 20;
                this.w = 20;
                this.h = 20;

                game.getRoom().getCamera().setZoom(5f);
            }
            @Override
            public void step() {
                x+=2;
            }

            @Override
            public void draw(Graphics g) {
                CameraRenderer cr = new CameraRenderer(g, room.getCamera());

                cr.drawSprite(sprite, (int) x+20, 100);
                cr.drawSprite(sprite, (int) x+200, 100);
            }
        };
    }
}
