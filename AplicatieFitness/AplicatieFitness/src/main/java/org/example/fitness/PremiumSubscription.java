package org.example.fitness;

public class PremiumSubscription extends Subscription {
    public PremiumSubscription(String clientName, Training training) {
        super(clientName, training);
    }

    @Override
    public double calculatePrice() {
        return getTraining().calculateFinalPrice() * 1.25;
    }
}