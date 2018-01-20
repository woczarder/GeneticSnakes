package dev.hdstudio.snakestest;

import java.awt.*;

/**
 * Created by woc on 05.10.2017.
 */
public class Program {
    Game game;

    World world;

    public Program(Game game) {
        this.game = game;

        world = new World(game);
    }

    // shiety

    public void tick() {
        world.tick();
    }

    public void render(Graphics2D g) {
        g.translate(100, 100);
        world.render(g);
    }

}
