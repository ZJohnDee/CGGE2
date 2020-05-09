package de.cg.cgge.files;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class FileContentsTest {

    private FileContents fileContents;

    @Before
    public void init(){
        ArrayList<String> contents = new ArrayList<>();
        contents.add("framerate: 60");
        contents.add("width: 1920");
        contents.add("height: 1080");
        contents.add("title: My Test Game");
        contents.add("taskbar: true");
        fileContents = new FileContents(contents);
    }

    @Test
    public void testGetFromKeywordHappyFlow(){
        String expectedResult = "60";
        String actualResult = fileContents.getFromKeyword("framerate");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetFromKeywordFail(){
        String expectedResult = null;
        String actualResult = fileContents.getFromKeyword("XYZ");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testAppendContent(){
        fileContents.append("XYZ:ABCD");
        String expectedResult = "ABCD";
        String actualResult = fileContents.getFromKeyword("XYZ");
        assertEquals(expectedResult, actualResult);
    }

}
