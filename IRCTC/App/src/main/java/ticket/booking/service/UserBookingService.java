package ticket.booking.service;

import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;
import ticket.booking.entities.Train;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserBookingService {
    private final List<User> users;
    private final List<Train> trains;

    public UserBookingService() throws IOException {
        users = new ArrayList<>();
        trains = new ArrayList<>();
    }


    public void signUp(User user) {
        users.add(user);
        System.out.println("User registered successfully.");
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getName().equals(username) && UserServiceUtil.checkPassword(password, user.getHashedPassword())) {
                return user;
            }
        }
        return null;
    }

    public void fetchBookings(User user) {
        System.out.println("Fetching bookings for: " + user.getName());
    }

    public List<Train> getTrains(String source, String destination) {
        return trains;
    }

    public void bookSeat(Scanner scanner, User user) {
        System.out.println("Feature coming soon...");
    }
}
