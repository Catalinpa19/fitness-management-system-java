package org.example.fitness;

public class TrainerPermanent extends Trainer {
    private double monthlySalary;

    public TrainerPermanent(String name, Training training, double monthlySalary) {
        super(name, training);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double getSalary() {
        return monthlySalary;
    }
}
