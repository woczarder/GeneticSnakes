package dev.hdstudio.snakestest;

import javax.swing.*;
import java.awt.*;

/**
 * Created by woc on 05.10.2017.
 */
public class Display {

    private JFrame frame;
    private Canvas canvas;

    public Display(int width, int height) {
        createDisplay(width, height);
    }

    private void createDisplay(int width, int height) {
        width = 600; height = 600;
        frame = new JFrame();
        frame.setSize(width, height);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                new ImageIcon("res/textures/cursor.png").getImage(),
                new Point(0,0),"custom cursor"));*/

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }

    public Canvas getCanvas(){
        return canvas;
    }


    public JFrame getFrame() {
        return frame;
    }
}