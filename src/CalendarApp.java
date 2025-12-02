import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CalendarApp {

    private static List<Event> events = new ArrayList<>();
    private static List<Reminder> reminders = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void run() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Ajouter un événement");
            System.out.println("2. Ajouter un rappel");
            System.out.println("3. Lister les rappels");
            System.out.println("4. Vérifier les rappels maintenant");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("❌ Entrée invalide !");
                continue;
            }

            switch (choice) {
                case 1 -> addEvent();
                case 2 -> addReminder();
                case 3 -> listReminders();
                case 4 -> checkReminders();
                case 0 -> System.out.println("➡️ Au revoir !");
                default -> System.out.println("❌ Choix invalide !");
            }
        }
    }

    private static void addEvent() {
        System.out.print("Titre de l'événement : ");
        String title = scanner.nextLine();

        System.out.print("Date et heure (YYYY-MM-DD HH:MM) : ");
        String dt = scanner.nextLine();

        try {
            LocalDateTime dateTime = LocalDateTime.parse(dt, formatter);
            events.add(new Event(title, dateTime));
            System.out.println("✔ Événement ajouté !");
        } catch (Exception e) {
            System.out.println("❌ Format de date invalide !");
        }
    }

    private static void addReminder() {
        System.out.print("Message du rappel : ");
        String message = scanner.nextLine();

        System.out.print("Date et heure (YYYY-MM-DD HH:MM) : ");
        String dt = scanner.nextLine();

        try {
            LocalDateTime dateTime = LocalDateTime.parse(dt, formatter);
            reminders.add(new Reminder(message, dateTime));
            System.out.println("✔ Rappel ajouté !");
        } catch (Exception e) {
            System.out.println("❌ Format de date invalide !");
        }
    }

    private static void listReminders() {
        if (reminders.isEmpty()) {
            System.out.println("Aucun rappel !");
            return;
        }

        System.out.println("\n--- Rappels ---");
        for (Reminder r : reminders) {
            System.out.println(r);
        }
    }

    private static void checkReminders() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("\n--- Vérification des rappels ---");

        boolean found = false;
        for (Reminder r : reminders) {
            if (r.getDateTime().isBefore(now) || r.getDateTime().isEqual(now)) {
                System.out.println("🔔 Rappel : " + r.getMessage());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Aucun rappel pour l’instant.");
        }
    }
}
