package org.example.fitness;


public abstract class Trainer {
    private String name;
    private Training training;

    public Trainer(String name, Training training) {
        this.name = name;
        this.training = training;
    }

    public String getName() { return name; }
    public Training getTraining() { return training; }

    public abstract double getSalary();

    @Override
    public String toString() {
        return name + " - " + training.getName();
    }
}