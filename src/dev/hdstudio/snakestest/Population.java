package dev.hdstudio.snakestest;

import java.awt.*;
import java.util.ArrayList;

public class Population {

    public int popSize = 1000;

    Snake[] individuals = new Snake[popSize];
    private double[] probs = new double[popSize];
    private double[] cumProbs = new double[popSize];
    private double[] randomNums = new double[popSize];

    private ArrayList<Integer> crossoverIndexes = new ArrayList<>();

    private double totalFitness;

    //Initialize population
    public void initializePopulation(int size) {
        for (int i = 0; i < popSize; i++) {
            individuals[i] = new Snake();
        }
    }

    //Calculate fitness of each individual
    public void calculateFitness() {
        for (Snake individual: individuals) {
            individual.calcFitness();
        }
    }

    // calculate total fitness
    public void calculateTotalFitness() {
        totalFitness = 0;
        for (Snake individual: individuals) {
            totalFitness += individual.getFitness();
        }
        System.out.println("Total fitness: "+(int)totalFitness);
    }

    // calculate probabilities for each individual. All for selection
    public void calculateProbs() {
        for (int i = 0; i < popSize; i++) {
            probs[i] = individuals[i].getFitness()/(1+totalFitness);
        }
    }

    // calculate cumulative probabilities
    public void calculateCumProbs() {
        for (int i = 0; i < popSize; i++) {
            for (int j = 0; j < i+1; j++) {
                cumProbs[i] += probs[j];
            }
            randomNums[i] = Math.random();
        }
    }

    // making babies
    public void selectFutureChromosome() {
        Snake[] oldIndiv = individuals;

        for (int i = 0; i < popSize; i++) {

            individuals[i] = oldIndiv[i];

            for (int j = 1; j < i+1; j++) {

                if (randomNums[i] > cumProbs[j-1]) {
                    individuals[i] = new Snake(oldIndiv[j]);
                }

            }
        }
    }

    // select snakes indexes to crossover
    public void selectCrossoverIndexes(double pc) {
        crossoverIndexes.clear();
        for (int i = 0; i < popSize; i++) {
            if (Math.random() < pc)
                crossoverIndexes.add(i);
        }
    }

    // crossover
    public void crossover() {
        if (crossoverIndexes.isEmpty())
            return;

        crossoverIndexes.add(crossoverIndexes.get(0));
        for (int i = 0; i < crossoverIndexes.size()-1; i++) {
            crossoverTwoSnakes(individuals[crossoverIndexes.get(i)], individuals[crossoverIndexes.get(i+1)]);
        }
    }

    // crossover two snakes
    public void crossoverTwoSnakes(Snake s1, Snake s2) {
        int crossPoint = getRandomNumberInRange(1, individuals[0].genes.length-1);
        for (int i = 0; i < crossPoint; i++) {
            s1.genes[i] = s2.genes[i];
        }

    }

    public void mutate(double mutationRate) {
        int mutationcount = (int) mutationRate * getTotalGenes();
        for (int i = 0; i < mutationcount; i++) {
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
