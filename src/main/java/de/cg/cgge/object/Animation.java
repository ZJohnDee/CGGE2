package de.cg.cgge.object;


import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class Animation {
    public static Animation DEFAULT = new Animation(100, 0);

    LinkedHashMap<Integer, Integer> frameTimings = new LinkedHashMap<>();

    int totalTicks;

    public Animation(File animationFile) {
        //TODO: Get all map indexes from a file and save to mapfile
        LinkedHashMap<Integer, Integer> animationFrameMap = new LinkedHashMap<>();

        int mapIndex = 0;
        for (Map.Entry<Integer, Integer> entry : animationFrameMap.entrySet()) {

            int length = entry.getValue();
            int frame = entry.getKey();

            for (int index = 0; index < length; index++) {
                frameTimings.put(index + mapIndex + 1, frame);
            }
            mapIndex += length;
        }

        totalTicks = mapIndex;
    }

    public Animation(LinkedHashMap<Integer, Integer> animationFrameMap) {

        int mapIndex = 0;
        for (Map.Entry<Integer, Integer> entry : animationFrameMap.entrySet()) {

            int length = entry.getValue();
            int frame = entry.getKey();

            for (int index = 0; index < length; index++) {
                frameTimings.put(index + mapIndex + 1, frame);
            }
            mapIndex += length;
        }

        totalTicks = mapIndex;
    }

    public Animation(Integer... integer) {
        LinkedHashMap<Integer, Integer> animationFrameMap = new LinkedHashMap<>();
        for (int index = 0; index < integer.length; index += 2) {
            animationFrameMap.put(integer[index], integer[index + 1]);
        }

        int mapIndex = 0;
        for (Map.Entry<Integer, Integer> entry : animationFrameMap.entrySet()) {

            int length = entry.getValue();
            int frame = entry.getKey();

            for (int index = 0; index < length; index++) {
                frameTimings.put(index + mapIndex + 1, frame);
            }
            mapIndex += length;
        }

        totalTicks = mapIndex;
    }

    public int getFrameFromTick(int tick) {
        return frameTimings.get(tick);
    }
}
