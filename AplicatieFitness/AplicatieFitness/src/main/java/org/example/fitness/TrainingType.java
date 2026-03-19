package org.example.fitness;

public enum TrainingType {
    YOGA("Yoga") {
        @Override
        public Training createTraining(String name, TrainingIntensity intensity, double basePrice) {
            return new Yoga(name, intensity, basePrice);
        }
    },
    PILATES("Pilates") {
        @Override
        public Training createTraining(String name, TrainingIntensity intensity, double basePrice) {
            return new Pilates(name, intensity, basePrice);
        }
    },
    CROSSFIT("CrossFit") {
        @Override
        public Training createTraining(String name, TrainingIntensity intensity, double basePrice) {
            return new CrossFit(name, intensity, basePrice);
        }
    };

    private final String displayName;

    TrainingType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public abstract Training createTraining(String name, TrainingIntensity intensity, double basePrice);
}