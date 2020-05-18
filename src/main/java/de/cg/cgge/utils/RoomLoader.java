package de.cg.cgge.utils;

import de.cg.cgge.files.FileContents;
import de.cg.cgge.files.GameFile;
import de.cg.cgge.game.GameInstance;
import de.cg.cgge.game.Room;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

public class RoomLoader {

    private GameInstance game;

    /**
     * RoomLoader is able to generate rooms from files created by the room editor
     * @param game The GameInstance that should be applied
     */
    public RoomLoader(GameInstance game) {
        this.game = game;
    }

    public Room loadRoom(GameFile roomFile)  {
        if (!roomFile.isLoaded()) throw new IllegalStateException(); //File must be loaded

        FileContents contents = roomFile.getContents();

        Room r = new Room(game); //The Room, the objects will be placed in

        /*LOOP THROUGH THE OBJECTS DEFINED IN THE FILE*/
        for (int i = 0; i<contents.getArrayList().size(); i++) {
            String[] parts = contents.getColumnsFromTableRow(i);

            String pckg = parts[0];

            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);

            int w = -1;
            int h = -1;

            //If width and height are part of the constructor, set the w and h values
            if (parts.length > 3) {
                w = Integer.parseInt(parts[3]);
                h = Integer.parseInt(parts[4]);
            }


            try {
                System.out.println("Trying to load object " + pckg + " with " + x + " / " + y + " / " + w + " / " + h);
                Class c = Class.forName(pckg);
                if (parts.length < 4) {
                    c.getConstructor(Room.class, Integer.TYPE, Integer.TYPE).newInstance(r,x,y);
                } else {
                    c.getConstructor(Room.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(r, x, y, w, h);
                }

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return r;
    }


}
