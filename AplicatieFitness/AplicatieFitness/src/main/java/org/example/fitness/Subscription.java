package org.example.fitness;

public abstract class Subscription {
    private String clientName;
    private Training training;

    public Subscription(String clientName, Training training) {
        this.clientName = clientName;
        this.training = training;
    }

    public String getClientName() { return clientName; }
    public Training getTraining() { return training; }

    public abstract double calculatePrice();

    @Override
    public String toString() {
        return clientName + " - " + training.getName() + " - " + calculatePrice() + " lei";
    }
}