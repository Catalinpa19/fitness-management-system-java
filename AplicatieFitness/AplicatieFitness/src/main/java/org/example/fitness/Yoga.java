package org.example.fitness;

public class Yoga extends Training {
    public Yoga(String name, TrainingIntensity intensity, double basePrice) {
        super(name, intensity, basePrice);
    }

    @Override
    public double calculateFinalPrice() {
        return getBasePrice();
    }
}