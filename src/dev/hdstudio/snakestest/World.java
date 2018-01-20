package dev.hdstudio.snakestest;

import java.awt.*;

public class World {
    Game game;

    Population population = new Population();
    int generationCount = 0;

    public World(Game game) {
        this.game = game;
        population.initializePopulation(10);
    }

    public void tick() {
        ++generationCount;

        evaluation();

        selection();

        crossover();

        mutation();

        System.out.println("Generation: " + generationCount + " Fittest: ");
    }

    //Evaluation
    private void evaluation() {
        population.calculateFitness();
    }

    //Selection
    private void selection() {
        population.calculateTotalFitness();
        population.calculateProbs();
        population.calculateCumProbs(); // and draw random numbers
        population.selectFutureChromosome(); // making beabbies
    }

    //Crossover
    private void crossover() {
        population.selectCrossoverIndexes(0.05);
        population.crossover();
    }

    //Mutation
    private void mutation() {
        population.mutate(0.05);
    }

    public void render(Graphics2D g) {
        int size = 25;
        g.setColor(Color.RED);
        g.fillOval(-size/2, -size/2, size, size);
        //g.translate(game.getWidth()/2, game.getHeight()/2);

        g.fillOval(game.getWidth()/2-size/2, game.getHeight()-size/2, size, size);
        population.render(g);
    }
}
