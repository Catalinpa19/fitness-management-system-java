package org.example.fitness;


public abstract class Training {
    private String name;
    private TrainingIntensity intensity;
    private double basePrice;

    public Training(String name, TrainingIntensity intensity, double basePrice) {
        this.name = name;
        this.intensity = intensity;
        this.basePrice = basePrice;
    }

    public String getName() { return name; }
    public TrainingIntensity getIntensity() { return intensity; }
    public double getBasePrice() { return basePrice; }

    public abstract double calculateFinalPrice();

    @Override
    public String toString() {
        return name + " (" + intensity + ") - " + calculateFinalPrice() + " lei";
    }
}

