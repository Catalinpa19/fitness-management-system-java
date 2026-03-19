package org.example.fitness;

public class Pilates extends Training {
    public Pilates(String name, TrainingIntensity intensity, double basePrice) {
        super(name, intensity, basePrice);
    }

    @Override
    public double calculateFinalPrice() {
        return getBasePrice() * 1.10;
    }
}