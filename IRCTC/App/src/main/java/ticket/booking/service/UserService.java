package ticket.booking.service;

import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;
import java.time.Instant;
import java.util.List;

public class UserService {
    private List<User> users;

    public UserService(List<User> users) {
        this.users = users;
    }

    public void signUp(User user) {
        users.add(user);
        System.out.println("User registered successfully.");
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getName().equals(username) && UserServiceUtil.checkPassword(password, user.getHashedPassword())) {
                // ✅ Successful login → Update last login time
                user.setLastLogin(Instant.now().toString());

                System.out.println("Login successful!");
                System.out.println("Welcome " + user.getName() + "!");
                System.out.println("Your last login was: " + user.getLastLogin());
                return user;
            }
        }
        System.out.println("Invalid username or password.");
        return null;
    }
}
