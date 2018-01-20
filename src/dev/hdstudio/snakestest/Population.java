package dev.hdstudio.snakestest;

import java.awt.*;

public class Population {

    public int popSize = 10;

    Snake[] individuals = new Snake[popSize];

    public double maxFitness;

    //Initialize population
    public void initializePopulation(int size) {
        for (int i = 0; i < popSize; i++) {
            individuals[i] = new Snake();
        }
    }

    //Calculate fitness of each individual
    public void calculateFitness() {
        maxFitness = 0;
        for (int i = 0; i < popSize; i++) {
            individuals[i].calcFitness();

            // calculate max fitness
            if (maxFitness < individuals[i].getFitness())
                maxFitness = individuals[i].getFitness();
        }
        System.out.println(maxFitness);
    }


    // crossover two snakes
    public void crossoverTwoSnakes(Snake s1, Snake s2) {
        int crossPoint = getRandomNumberInRange(1, individuals[0].genes.length-1);
        for (int i = 0; i < crossPoint; i++) {
            s1.genes[i] = s2.genes[i];
        }

    }

    public void mutate(double mutationRate) {
        int mutationCount = (int) mutationRate * getTotalGenes();
        for (int i = 0; i < mutationCount; i++) {
            int position = getRandomNumberInRange(0, getTotalGenes()-1);
            int x = position/individuals[0].genes.length;
            int y = position%individuals[0].genes.length;
            mutateSnake(individuals[x], y);
        }
    }

    private void mutateSnake(Snake snake, int index) {
        snake.genes[index] = Math.random()*Math.PI;
    }

    private int getTotalGenes() {
        return popSize*individuals[0].genes.length;
    }

    // shiets

    public void tick() {
        for (Snake individual: individuals) {
            individual.tick();
        }

    }

    public void render(Graphics2D g) {
        for (int i = 0; i < individuals.length; i++) {
            individuals[i].render(g);
        }
    }

    public static double distance(double x, double y, double x2, double y2) {
        return ((float) Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2)));
    }

    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return (int)(Math.random() * ((max - min) + 1)) + min;
    }
}
