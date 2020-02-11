package de.cg.cgge.ctrl;

import java.util.ArrayList;

import de.cg.cgge.game.GameInstance;
import de.cg.cgge.game.GameObject;

public class Clock extends Thread {

    protected final GameInstance game;

    protected boolean running = false;
    protected boolean ranOnce = false;

    public double deltaTime = 0; 

    /**
     * Initializes the main clock of the game.
     * By overriding protected void clockAction(), you can make your own clock, which is called every frame
     * @param game GameInstance, the clock should rely to
     */

    public Clock(GameInstance game) {
        this.game = game;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ranOnce = true; 
        running = true; 

        game.getDrawer().setStartTime(System.currentTimeMillis()/1000);

        final double FPS = game.getFramerate();
        long lastTime = System.nanoTime(); 
        double neededTime = 1000000000/FPS; 
        double deltaTime = 0; 

        //Main loop
        while(running) {
            long currentTime = System.nanoTime(); 
            deltaTime += (currentTime - lastTime) / neededTime;
            lastTime = currentTime; 

            if (deltaTime >= 1) {
                this.deltaTime = deltaTime; 
                deltaTime -= 1; 
                clockAction();
            }
        }

        this.stop(); 

    }

    public void stopClock() {
        running = false; 
    }

    protected void clockAction() {        

        game.getDrawer().getRoom().getCamera().update();

        ArrayList<GameObject> objects = game.getDrawer().getRoom().getObjectManager().getObjects();

        game.getDrawer().getRoom().getObjectManager().updateObjects();

        //for (GameObject obj : objects) {
        //    obj.preStep();
        //}

        for (GameObject obj : objects) {
            obj.step();
        }
        

    }

    public boolean ranOnce() {
        return this.ranOnce; 
    }

    public boolean isRunning() {
        return this.running; 
    }



}