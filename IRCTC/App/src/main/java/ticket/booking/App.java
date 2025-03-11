package ticket.booking;

import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.service.UserBookingService;
import ticket.booking.util.UserServiceUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class App {

    public static void main(String[] args) {
        System.out.println("Welcome to Train Booking System");
        Scanner scanner = new Scanner(System.in);
        int option;
        UserBookingService userBookingService;

        try {
            userBookingService = new UserBookingService();
        } catch (IOException ex) {
            System.out.println("Error initializing system.");
            return;
        }

        User loggedInUser = null;

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Trains");
            System.out.println("5. Book a Seat");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1 -> {
                    System.out.print("Enter username: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    String hashedPassword = UserServiceUtil.hashPassword(password);
                    User newUser = new User(name, email, hashedPassword, UUID.randomUUID().toString());
                    userBookingService.signUp(newUser);
                    System.out.println("Sign-up successful! Please log in.");
                }
                case 2 -> {
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    loggedInUser = userBookingService.login(username, password);
                    if (loggedInUser != null) {
                        String loginTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                        System.out.println("Login successful at " + loginTime);
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                }
                case 3 -> {
                    if (loggedInUser == null) {
                        System.out.println("Please log in first.");
                    } else {
                        userBookingService.fetchBookings(loggedInUser);
                    }
                }
                case 4 -> {
                    System.out.print("Enter source station: ");
                    String source = scanner.nextLine();
                    System.out.print("Enter destination station: ");
                    String destination = scanner.nextLine();

                    List<Train> trains = userBookingService.getTrains(source, destination);
                    if (trains.isEmpty()) {
                        System.out.println("No trains available for this route.");
                    } else {
                        for (int i = 0; i < trains.size(); i++) {
                            Train t = trains.get(i);
                            System.out.println((i + 1) + ". Train ID: " + t.getTrainId());
                            for (Map.Entry<String, String> entry : t.getStationTimes().entrySet()) {
                                System.out.println("Station: " + entry.getKey() + " Time: " + entry.getValue());
                            }
                        }
                    }
                }
                case 5 -> {
                    if (loggedInUser == null) {
                        System.out.println("Please log in first.");
                    } else {
                        userBookingService.bookSeat(scanner, loggedInUser);
                    }
                }
                case 6 -> {
                    System.out.println("Exiting application.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
