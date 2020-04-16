package de.cg.cgge.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import de.cg.cgge.io.KeyManager;
import de.cg.cgge.io.MouseManager;
import de.cg.cgge.io.WindowListener;

import java.awt.*;

public class Window extends JFrame {
    private static final long serialVersionUID = 1L;

    private KeyManager keyManager;

    private boolean isFullScreen;

    private Drawer drawer;

    private JPanel drawPanel;

    public Window(Drawer drawer) {
        super(drawer.getGameInstance().getTitle());
        this.drawer = drawer;   
        
        //Setting up basic settings
        drawPanel = new DrawPanel(drawer); 
        setUndecorated(!drawer.getGameInstance().isTaskbarActive());
        setResizable(true);
        setVisible(true);
        setFocusable(false);
        addComponentListener(new WindowListener());
        setBounds(50, 50, drawer.getGameInstance().getWidth(), drawer.getGameInstance().getHeight());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        //Creating the draw panel
        keyManager = new KeyManager(drawer);
        drawPanel.setBounds(0, 0, drawer.getGameInstance().getWidth(), drawer.getGameInstance().getHeight());
        drawPanel.addKeyListener(keyManager);
        drawPanel.addMouseListener(new MouseManager(drawer));

        //Focusing and adding the draw panel
        drawPanel.setFocusable(true);
        add(drawPanel);
        drawPanel.requestFocus();
        drawPanel.requestFocusInWindow();

    }

    public JPanel getDrawPanel() {
        return this.drawPanel;
    }

    public Drawer getDrawer() {
        return this.drawer;
    }

    public boolean isFullScreen() {
        return isFullScreen;
    }

    /**
     * Switches between screen modes.
     * @return Whether it's in full screen mode or not
     */
    public boolean switchFullScreen() {

        if (drawer.getGameInstance().isTaskbarActive()) {
            throw new IllegalStateException("Tried to change full screen mode of window with activated Task Bar");
        }


        if (!isFullScreen) {
            GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice device = graphics.getDefaultScreenDevice();

            device.setFullScreenWindow(this);
            isFullScreen = true;

        } else {
            GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice device = graphics.getDefaultScreenDevice();

            device.setFullScreenWindow(null);

            isFullScreen = false;
        }


        return isFullScreen;
    }

    public KeyManager getKeyManger() {
        return this.keyManager;
    }

}