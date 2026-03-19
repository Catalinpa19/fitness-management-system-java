package org.example.fitness;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        FitnessSystem sys = new FitnessSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMainMenu();
            int opt = getInt(sc, "Alege o optiune: ");

            switch (opt) {
                case 1 -> addTraining(sys, sc);
                case 2 -> addTrainer(sys, sc);
                case 3 -> addSubscription(sys, sc);
                case 4 -> removeTraining(sys, sc);
                case 5 -> removeTrainer(sys, sc);
                case 6 -> removeSubscription(sys, sc);
                case 7 -> sys.printReport();
                case 8 -> {
                    System.out.println("La revedere!");
                    return;
                }
                default -> System.out.println("Optiune invalida.");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n================== FITZONE+ ==================");
        System.out.println("1. Adauga antrenament");
        System.out.println("2. Adauga antrenor");
        System.out.println("3. Adauga abonament");
        System.out.println("4. Sterge antrenament");
        System.out.println("5. Sterge antrenor");
        System.out.println("6. Sterge abonament");
        System.out.println("7. Afiseaza raport");
        System.out.println("8. Iesire");
        System.out.println("==============================================");
    }

    private static void addTraining(FitnessSystem sys, Scanner sc) {
        String name = getString(sc, "Nume antrenament: ");

        TrainingIntensity[] intensities = TrainingIntensity.values();
        for (int i = 0; i < intensities.length; i++)
            System.out.println((i + 1) + ". " + intensities[i]);

        TrainingIntensity intensity =
                intensities[getInt(sc, "Alege intensitatea: ") - 1];

        double price = getDouble(sc, "Pret baza: ");

        TrainingType[] types = TrainingType.values();
        for (int i = 0; i < types.length; i++)
            System.out.println((i + 1) + ". " + types[i].getDisplayName());

        TrainingType type =
                types[getInt(sc, "Tip antrenament: ") - 1];

        Training t = type.createTraining(name, intensity, price);
        sys.addTraining(t);
        System.out.println("Antrenament adaugat!");
    }

    private static void addTrainer(FitnessSystem sys, Scanner sc) {
        if (sys.getTrainings().isEmpty()) {
            System.out.println("Adauga intai antrenamente.");
            return;
        }

        String name = getString(sc, "Nume trainer: ");

        List<Training> trainings = sys.getTrainings();
        for (int i = 0; i < trainings.size(); i++)
            System.out.println((i + 1) + ". " + trainings.get(i).getName());

        int idx = getInt(sc, "Alege antrenamentul: ") - 1;
        if (idx < 0 || idx >= trainings.size()) return;

        Training chosen = trainings.get(idx);

        System.out.println("1. Permanent");
        System.out.println("2. Extern");

        int type = getInt(sc, "Tip trainer: ");

        Trainer trainer;
        if (type == 1) {
            double sal = getDouble(sc, "Salariu lunar: ");
            trainer = new TrainerPermanent(name, chosen, sal);

        } else if (type == 2) {
            double fee = getDouble(sc, "Plata per sesiune: ");
            trainer = new TrainerExtern(name, chosen, fee);

        } else {
            System.out.println("Tip invalid.");
            return;
        }

        sys.addTrainer(trainer);
        System.out.println("Trainer adaugat!");
    }

    private static void addSubscription(FitnessSystem sys, Scanner sc) {
        if (sys.getTrainings().isEmpty()) {
            System.out.println("Adauga intai antrenamente.");
            return;
        }

        String client = getString(sc, "Nume client: ");

        List<Training> trainings = sys.getTrainings();
        for (int i = 0; i < trainings.size(); i++)
            System.out.printf("%d. %s (%.2f lei)\n",
                    i + 1, trainings.get(i).getName(), trainings.get(i).calculateFinalPrice());

        int idx = getInt(sc, "Alege antrenamentul: ") - 1;
        if (idx < 0 || idx >= trainings.size()) return;

        Training chosen = trainings.get(idx);

        SubscriptionType[] types = SubscriptionType.values();
        for (int i = 0; i < types.length; i++)
            System.out.println((i + 1) + ". " + types[i].getDisplayName());

        SubscriptionType type =
                types[getInt(sc, "Tip abonament: ") - 1];

        Subscription sub = type.createSubscription(client, chosen);
        sys.addSubscription(sub);

        System.out.println("Abonament adaugat!");
    }

    private static void removeTraining(FitnessSystem sys, Scanner sc) {
        List<Training> trainings = sys.getTrainings();
        if (trainings.isEmpty()) {
            System.out.println("Nu exista antrenamente.");
            return;
        }

        for (int i = 0; i < trainings.size(); i++)
            System.out.println((i + 1) + ". " + trainings.get(i).getName());

        int idx = getInt(sc, "Alege: ") - 1;
        if (sys.removeTraining(idx))
            System.out.println("Antrenament sters.");
        else
            System.out.println("Index invalid.");
    }

    private static void removeTrainer(FitnessSystem sys, Scanner sc) {
        List<Trainer> trainers = sys.getTrainers();
        if (trainers.isEmpty()) {
            System.out.println("Nu exista antrenori.");
            return;
        }

        for (int i = 0; i < trainers.size(); i++)
            System.out.println((i + 1) + ". " + trainers.get(i).getName());

        int idx = getInt(sc, "Alege: ") - 1;
        if (sys.removeTrainer(idx))
            System.out.println("Trainer sters.");
        else
            System.out.println("Index invalid.");
    }

    private static void removeSubscription(FitnessSystem sys, Scanner sc) {
        List<Subscription> subs = sys.getSubscriptions();
        if (subs.isEmpty()) {
            System.out.println("Nu exista abonamente.");
            return;
        }

        for (int i = 0; i < subs.size(); i++)
            System.out.println((i + 1) + ". " + subs.get(i).getClientName());

        int idx = getInt(sc, "Alege: ") - 1;
        if (sys.removeSubscription(idx))
            System.out.println("Abonament sters.");
        else
            System.out.println("Index invalid.");
    }

    private static int getInt(Scanner sc, String text) {
        System.out.print(text);
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Introdu un numar: ");
        }
        int v = sc.nextInt();
        sc.nextLine();
        return v;
    }

    private static double getDouble(Scanner sc, String text) {
        System.out.print(text);
        while (!sc.hasNextDouble()) {
            sc.next();
            System.out.print("Introdu un numar: ");
        }
        double v = sc.nextDouble();
        sc.nextLine();
        return v;
    }

    private static String getString(Scanner sc, String text) {
        System.out.print(text);
        return sc.nextLine().trim();
    }
}
