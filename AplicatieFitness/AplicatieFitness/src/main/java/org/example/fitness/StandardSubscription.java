package org.example.fitness;

public class StandardSubscription extends Subscription {
    public StandardSubscription(String clientName, Training training) {
        super(clientName, training);
    }

    @Override
    public double calculatePrice() {
        return getTraining().calculateFinalPrice();
    }
}