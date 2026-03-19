package org.example.fitness;

public class TrainerExtern extends Trainer {
    private double sessionFee;

    public TrainerExtern(String name, Training training, double sessionFee) {
        super(name, training);
        this.sessionFee = sessionFee;
    }

    @Override
    public double getSalary() {
        return sessionFee;
    }
}