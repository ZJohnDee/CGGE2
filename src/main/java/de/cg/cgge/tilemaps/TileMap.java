package de.cg.cgge.tilemaps;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.cg.cgge.files.FileContents;
import de.cg.cgge.files.GameFile;

import java.awt.image.BufferedImage;
import java.awt.*;

import de.cg.cgge.game.*;

import static de.cg.cgge.utils.StringConstants.COMMA;

public class TileMap {

    private int mapWidth, mapHeight;
    private int scale;
    private int tileSize;

    private Room room; 

    private Tile[][][] drawLayers;
    private boolean[][] collisionLayer;
    private String[][] actionLayer;
    private String tileSetPath = "";

    private BufferedImage tileSet;

    /**
     * Creates the tile map It also loads the tile set from the instructions given
     * in the TileMap file.
     * 
     * @param path The path of the *.tilemap file.
     * @param scale The scale of each tile
     * @param room The current room
     */
    public TileMap(String path, int scale, Room room) {
        try {

            this.room = room;

            GameFile gf = new GameFile(path);

            this.scale = scale; 

            FileContents fc = gf.getContents();

            String lineOne = fc.get()[0];

            // Setup basic settings
            String[] lineOneParts = lineOne.split(COMMA);
            setTileSetPath(lineOneParts[0]);
            setTileSize(Integer.parseInt(lineOneParts[1]));
            setMapWidth(Integer.parseInt(lineOneParts[2]));
            setMapHeight(Integer.parseInt(lineOneParts[3]));

            // Load the image
            tileSet = ImageIO.read(new File(tileSetPath));

            //Create layers
            drawLayers = new Tile[2][mapHeight+1][mapWidth+1];
            collisionLayer = new boolean[mapHeight+1][mapWidth+1];
            actionLayer = new String[mapHeight+1][mapWidth+1];

            String[] l1 = fc.get()[1].split(COMMA);
            String[] l2 = fc.get()[2].split(COMMA);
            String[] lc = fc.get()[3].split(COMMA);
            String[] la = fc.get()[4].split(COMMA);

            String[][] layers = new String[4][l1.length];
            

            
            for (int i = 0; i<l1.length; i++) {
                layers[0][i] = l1[i];
            }

            for (int i = 0; i<l1.length; i++) {
                layers[1][i] = l2[i];
            }

            for (int i = 0; i<lc.length; i++) {
                layers[2][i] = lc[i];
            }

            for (int i = 0; i<la.length; i++) {
                layers[3][i] = la[i];
            }



            for (int l = 0; l<3; l++)
            for (int y = 0; y<mapHeight; y++) {
                for (int x = 0; x<mapWidth; x++) {
                    if (l <= 1) {
                        int section = Integer.parseInt(layers[l][x+y*2]);
                        drawLayers[l][y][x] = new Tile(this, section, tileSize, scale);
                    } else if (l == 2) {
                        String c = layers[l][x+y*2];
                        collisionLayer[y][x] = (c.equals("x"));
                    } else if (l == 3) {
                        String a = layers[l][x+y*2];
                        actionLayer[y][x] = a; 
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    /**
     * Draws the tile map according to the scale
     * It also respects the camera of the room, when it's active
     * @param g Graphics component
     * @param layer The layer to be drawn
     */
    public void draw(Graphics g, int layer) {

        if (drawLayers != null) {
            for (int y = 0; y<mapHeight; y++) {
                for (int x = 0; x<mapWidth; x++) {
                    Tile tile = drawLayers[layer][y][x];
                    if (tile.getSection() != 1) {
                        tile.draw(g, x, y);
                    }
                }
            }
        }
    }

    public BufferedImage getTileSet() {
        return tileSet;
    }

    public void setTileSet(BufferedImage tileSet) {
        this.tileSet = tileSet;
    }

    public Room getRoom() {
        return this.room; 
    }

    public String getTileSetPath() {
        return tileSetPath;
    }

    public void setTileSetPath(String tileSetPath) {
        this.tileSetPath = tileSetPath;
    }

    public String[][] getActionLayer() {
        return actionLayer;
    }

    public void setActionLayer(String[][] actionLayer) {
        this.actionLayer = actionLayer;
    }

    public boolean[][] getCollisionLayer() {
        return collisionLayer;
    }

    public void setCollisionLayer(boolean[][] collisionLayer) {
        this.collisionLayer = collisionLayer;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public boolean isCollision(int x, int y) {
        return collisionLayer[y][x];
    }

    public String getAction(int x, int y) {
        return this.actionLayer[y][x];
    }

}