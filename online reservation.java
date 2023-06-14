import java.lang.Exception;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Online_Reservation_System {
    private static Map<String, User> users = new HashMap<>();
    private static Map<Integer, Reservation> reservations = new HashMap<>();
    private static int pnrCounter = 1;

    public static void main(String[] args) {
        // Creating some sample users
        users.put("Shivang", new User("Shivang01", "123456"));
        users.put("Mai", new User("shivang02", "987654"));
        users.put("ShivangP", new User("Oasis", "456258"));

        // Login Form
        boolean loggedIn = false;
        Scanner scanner = new Scanner(System.in);

        while (!loggedIn) {
            System.out.print("\n\n\n\nEnter your username: ");
            String username = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            User user = users.get(username);
            if (user != null && user.getPassword().equals(password)) {
                System.out.println("Login successful!");
                loggedIn = true;
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        }

        // Reservation System
        System.out.println("\nReservation Form");
        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();
        System.out.print("Enter class type: ");
        String classType = scanner.nextLine();
        System.out.print("Enter date of journey: ");
        String dateOfJourney = scanner.nextLine();
        System.out.print("Enter from (place): ");
        String from = scanner.nextLine();
        System.out.print("Enter destination: ");
        String to = scanner.nextLine();

        // Generate PNR number
        int pnrNumber = generatePnrNumber();

        Reservation reservation = new Reservation(pnrNumber, trainNumber, "Sample Train", classType, dateOfJourney, from, to);
        reservations.put(pnrNumber, reservation);

        System.out.println("\nReservation details:");
        System.out.println("PNR Number: " + pnrNumber);
        System.out.println("Train Number: " + trainNumber);
        System.out.println("Train Name: " + reservation.getTrainName());
        System.out.println("Class Type: " + classType);
        System.out.println("Date of Journey: " + dateOfJourney);
        System.out.println("From: " + from);
        System.out.println("To: " + to);

        // Cancellation Form
        System.out.println("\nCancellation Form");
        System.out.print("Enter your PNR number: ");
        int cancelPnrNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        Reservation cancelReservation = reservations.get(cancelPnrNumber);
        if (cancelReservation != null) {
            System.out.println("\nReservation details:");
            System.out.println("PNR Number: " + cancelReservation.getPnrNumber());
            System.out.println("Train Number: " + cancelReservation.getTrainNumber());
            System.out.println("Train Name: " + cancelReservation.getTrainName());
            System.out.println("Class Type: " + cancelReservation.getClassType());
            System.out.println("Date of Journey: " + cancelReservation.getDateOfJourney());
            System.out.println("From: " + cancelReservation.getFrom());
            System.out.println("To: " + cancelReservation.getTo());

            System.out.print("Press OK to confirm cancellation (OK/Cancel): ");
            String confirmCancellation = scanner.nextLine();

            if (confirmCancellation.equalsIgnoreCase("OK")) {
                reservations.remove(cancelPnrNumber);
                System.out.println("Reservation with PNR number " + cancelPnrNumber + " has been cancelled.");
            } else {
                System.out.println("Cancellation not confirmed.");
            }
        } else {
            System.out.println("Reservation with PNR number " + cancelPnrNumber + " not found.");
        }

        System.out.println("\nThank you for using the Online Reservation System.");
    }

    private static int generatePnrNumber() {
        return pnrCounter++;
    }
}
