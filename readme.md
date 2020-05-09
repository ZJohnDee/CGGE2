<h1>CGGE2</h1>
CGGE2 is an easy to learn, fully object orientated, simple 2D Game Engine written in Java.

It contains several features:
  - Easy managment of Game Objects
  - Sprites, Animations, Sounds, Easy File System
  - Camera
  - Simple and expandable Physics Engine (Moving objects with collisions in mind; Gravity)
  - TileSet system
  - Support for multiple rooms (Scenes)
  - Utils
  - Fully documented

<h1>How to use?</h1>
Just download a release and include the jar into your project. Optionally, you can also include the javadocs as well.

Read the documentation included in the download for further information.

<h1>What's next?</h1>
Here's the roadmap for the year:
<table>
  <tr>
    <td>May 2020</td>
    <td>First Stable Release</td>
  </tr>
  <tr>
    <td>July 2020</td>
    <td>TileEditor and UI-Package</td>
  <tr>
    <td>October 2020</td>
    <td>Version 2.0 Pre-Release with several planned improvements</td>
  </tr>
  <tr>
    <td>December 2020</td>
    <td>Release of Version 2.0 and a simple room builder.</td>
  </tr>
</table>

<h1>Code Examples</h1>
<h4>Simple example of a moving player</h4>
App.java

```JAVA
public class App {
    public static void main(String[] args) {
        GameInstance game = new GameInstance("gameConfig.data");
        GameObject player = new Player(game.getRoom());
    }
}
```

Player.java

```JAVA
/* Needs to extend the GameObject class */

import de.cg.cgge.game.GameObject;
import de.cg.cgge.game.Room;
import de.cg.cgge.io.KeyManager;
import de.cg.cgge.physics.Physics;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends GameObject {
        
    final int speed = 300;
    KeyManager keys = room.getKeyManager();

    public Player(Room room) {
        super(room);

        this.x = 20f;
        this.y = 20f;
        this.w = 20;
        this.h = 20;

    }

    @Override
    public void step() {
        //Getting delta time to be framerate independent
        float delta = Physics.deltaTime(room.getGameInstance());
    

        /* Moving based on key input */

        if(keys.checkKey(KeyEvent.VK_W)) {
            y -= speed * delta;
        }
        if(keys.checkKey(KeyEvent.VK_S)) {
            y += speed * delta;
        }
        if(keys.checkKey(KeyEvent.VK_A)) {
            x -= speed * delta;
        }
        if(keys.checkKey(KeyEvent.VK_D)) {
            x += speed * delta;
        }

    }
    
    @Override
    public void draw(Graphics g) {
        g.fillRect((int) x, (int) y, w, h); //Drawing the player as a rectangle
    }   
   
}
```
