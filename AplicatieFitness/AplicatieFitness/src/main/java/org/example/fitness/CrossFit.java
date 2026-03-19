package org.example.fitness;

public class CrossFit extends Training {
    public static final double DOUBLE = 1.20;

    public CrossFit(String name, TrainingIntensity intensity, double basePrice) {
        super(name, intensity, basePrice);
    }

    @Override
    public double calculateFinalPrice() {
        switch (getIntensity()) {
            case USOR:
                return getBasePrice();
            case MEDIU:
                return getBasePrice() * DOUBLE;
            case GREU:
                return getBasePrice() * 1.40;
            default:
                return getBasePrice();
        }
    }
}
