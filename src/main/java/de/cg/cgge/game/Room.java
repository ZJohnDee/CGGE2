package de.cg.cgge.game;

import de.cg.cgge.ctrl.Clock;
import de.cg.cgge.io.KeyManager;
import de.cg.cgge.tilemaps.TileMap;

public class Room {

    private boolean usesTileMap = false; 

    private ObjectManager om;
    private Clock clock; 
    private GameInstance game; 

    private Camera camera;

    private TileMap tileMap; 

    /**
     * Creates a room instance
     * This contains a clock instance, which is then started
     * It also creates a camera, which does not follow any object
     * This also creates an de.cg.cgge.game.ObjectManager
     * @param game The GameInstance, the room belongs to
     */
    public Room(GameInstance game) {
        om = new ObjectManager();
        this.game = game; 

        camera = new Camera(null, this);

        clock = new Clock(game);
    }

    public ObjectManager getObjectManager() {
        return this.om; 
    }

    public Clock getClock() {
        return this.clock; 
    }

    public GameInstance getGameInstance() {
        return this.game; 
    }

    public Camera getCamera() {
        return camera; 
    }

    /**
     * 
     * @return TileMap, the room has currently in place
     */
    public TileMap getTileMap() {
        return tileMap; 
    }

    public boolean usesTileMap() {
        return usesTileMap;
    }

    /**
     * Create a tile map
     * Also loads in the TileSet defined in the tile map file
     * @param path The *.tilemap file
     * @param scale The scale, each tile should be displayed at
     */
    public void initTileMap(String path, int scale) {
        tileMap = new TileMap(path, scale, this);
        usesTileMap = true; 
    }

    public void setClock(Clock clock) {
        this.clock = clock; 
    } 

    public KeyManager getKeyManager() {
        return getGameInstance().getDrawer().getWindow().getKeyManger();
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}