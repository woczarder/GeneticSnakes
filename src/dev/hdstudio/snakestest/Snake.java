package dev.hdstudio.snakestest;

import java.awt.*;

public class Snake {
    public static int speed = 10;

    //public static int originX, originY;

    public double genes[];
    private double x, y, xi, yi;
    private double lastX, lastY;

    private double fitness;

    public Snake() {
        genes = new double[25];
        x = y = 0;
        xi = yi = 0;

        for (int i = 0; i < genes.length; i++) {
            genes[i] = Math.random()*Math.PI;
        }
    }

    public Snake(Snake s) {
        genes = s.genes;
        x = y = 0;
        xi = yi = 0;
    }

    public void calcFitness() {
        fitness = 1/1+Population.distance(lastX, lastY, 160, 180);
    }

    public void tick() {
        for (int i = 0; i < genes.length; i++) {
            genes[i] = Math.random()*Math.PI;
        }
        //calcFitness();
        //System.out.println(fitness);
    }

    public void render(Graphics2D g) {
        prettySnakeRender(g);
        g.setColor(Color.blue);
    }

    private void prettySnakeRender(Graphics2D g) {
        for (int i = 0; i < genes.length; i++) {
            yi = Math.sin(genes[i]) * speed;
            xi = Math.cos(genes[i]) * speed;

            g.drawLine((int)x, (int)y, (int)(x+xi), (int)(y+yi));

            x = x+xi;
            y = y+yi;

            if (i == genes.length-1) {
                lastX = x;
                lastY = y;
            }
        }
        x = y = 0;
    }

    private void uglySnakeRender(Graphics2D g) {
        for (int i = 0; i < genes.length; i++) {
            g.drawLine(0, 0, 0, speed);
            g.translate(0, speed);
            g.rotate(genes[i]);
        }
    }


    public double getFitness() {
        return fitness;
    }

    public int getChromosomeLength() {
        return genes.length;
    }

}
