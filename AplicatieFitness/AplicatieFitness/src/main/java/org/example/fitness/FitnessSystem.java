package org.example.fitness;

import java.util.ArrayList;
import java.util.List;

public class FitnessSystem {
    private List<Trainer> trainers = new ArrayList<>();
    private List<Subscription> subscriptions = new ArrayList<>();
    private List<Training> trainings = new ArrayList<>();

    public void addTrainer(Trainer t) { trainers.add(t); }
    public void addSubscription(Subscription s) { subscriptions.add(s); }
    public void addTraining(Training t) { trainings.add(t); }

    public boolean removeTraining(int index) {
        if (index < 0 || index >= trainings.size()) return false;

        Training removed = trainings.get(index);

        trainers.removeIf(t -> t.getTraining() == removed);
        subscriptions.removeIf(s -> s.getTraining() == removed);

        trainings.remove(index);
        return true;
    }

    public boolean removeTrainer(int index) {
        if (index < 0 || index >= trainers.size()) return false;
        trainers.remove(index);
        return true;
    }

    public boolean removeSubscription(int index) {
        if (index < 0 || index >= subscriptions.size()) return false;
        subscriptions.remove(index);
        return true;
    }

    public List<Training> getTrainings() { return trainings; }
    public List<Trainer> getTrainers() { return trainers; }
    public List<Subscription> getSubscriptions() { return subscriptions; }

    public void printReport() {
        System.out.println("\n==================================================");
        System.out.println("                  RAPORT FITZONE+                ");
        System.out.println("==================================================");

        printTrainings();
        printTrainers();
        printSubscriptions();
        printSummary();
    }

    private void printTrainings() {
        System.out.println("\n-- ANTRENAMENTE --");

        if (trainings.isEmpty()) {
            System.out.println("Nu exista antrenamente.");
            return;
        }

        for (int i = 0; i < trainings.size(); i++) {
            Training t = trainings.get(i);
            System.out.printf("%d. %s (%s) - %.2f lei\n",
                    i + 1, t.getName(), t.getIntensity(), t.calculateFinalPrice());
        }
    }

    private void printTrainers() {
        System.out.println("\n-- ANTRENORI --");

        if (trainers.isEmpty()) {
            System.out.println("Nu exista antrenori.");
            return;
        }

        for (Training tr : trainings) {
            boolean headerPrinted = false;

            for (Trainer t : trainers) {
                if (t.getTraining() == tr) {
                    if (!headerPrinted) {
                        System.out.println("Antrenament: " + tr.getName());
                        headerPrinted = true;
                    }

                    String type = (t instanceof TrainerPermanent) ? "Permanent" : "Extern";
                    System.out.printf("   * %s - %s (%.2f lei)\n",
                            t.getName(), type, t.getSalary());
                }
            }
        }
    }

    private void printSubscriptions() {
        System.out.println("\n-- ABONAMENTE --");

        if (subscriptions.isEmpty()) {
            System.out.println("Nu exista abonamente.");
            return;
        }

        for (Subscription s : subscriptions) {
            System.out.printf("%s - %s (%.2f lei)\n",
                    s.getClientName(),
                    s.getTraining().getName(),
                    s.calculatePrice());
        }
    }

    private void printSummary() {
        double totalTrainerCost = trainers.stream().mapToDouble(Trainer::getSalary).sum();
        double totalRevenue = subscriptions.stream().mapToDouble(Subscription::calculatePrice).sum();
        double profit = totalRevenue - totalTrainerCost;

        System.out.println("\n-- REZUMAT FINANCIAR --");
        System.out.printf("Venit total: %.2f lei\n", totalRevenue);
        System.out.printf("Cost antrenori: %.2f lei\n", totalTrainerCost);
        System.out.printf("Profit: %.2f lei\n", profit);

        System.out.println("\n-- POPULARITATE ANTRENAMENTE --");
        for (Training tr : trainings) {
            long count = subscriptions.stream()
                    .filter(s -> s.getTraining() == tr)
                    .count();

            if (count > 0) {
                System.out.printf("%s: %d abonamente\n", tr.getName(), count);
            }
        }
    }
}
