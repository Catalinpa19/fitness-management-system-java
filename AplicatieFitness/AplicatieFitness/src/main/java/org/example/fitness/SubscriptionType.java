package org.example.fitness;

public enum SubscriptionType {
    STANDARD("Standard") {
        @Override
        public Subscription createSubscription(String clientName, Training training) {
            return new StandardSubscription(clientName, training);
        }
    },
    PREMIUM("Premium") {
        @Override
        public Subscription createSubscription(String clientName, Training training) {
            return new PremiumSubscription(clientName, training);
        }
    };



    private final String displayName;

    SubscriptionType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public abstract Subscription createSubscription(String clientName, Training training);
}