package dev.hdstudio.snakestest;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by woc on 05.10.2017.
 */
public class Game {

    private Display display;
    private Program program;
    private final int width = 320,
            height = 180;

    private BufferStrategy bs;
    private Graphics2D g2;

    public Game() {
        init();

        //GAME LOOP
        int fps = 10;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(true){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1){
                tick();
                render();
                Toolkit.getDefaultToolkit().sync(); // For Linux
                ticks++;
                delta--;
            }

            if(timer >= 1000000000){
                //System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
    }

    private void init() {
        display = new Display(width, height);
        program = new Program(this);
    }

    private void tick(){
        program.tick();
    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g2 = (Graphics2D) bs.getDrawGraphics();
        //Clear Screen
        //g.clearRect(0, 0, width, height);
        g2.clearRect(0, 0, 1000, 1000);
        //Draw Here!

        g2.setColor(Color.GRAY);
        g2.fillRect(0,0, width, height);
        program.render(g2);

        //End Drawing!
        bs.show();
        g2.dispose();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
