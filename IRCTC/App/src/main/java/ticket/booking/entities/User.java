package ticket.booking.entities;

import java.time.Instant;

public class User {
    private final String name;
    private final String email;
    private final String hashedPassword;
    private final String userId;
    private String lastLogin;  // New field to store last login timestamp

    // Constructor
    public User(String name, String email, String hashedPassword, String userId) {
        this.name = name;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.userId = userId;
        this.lastLogin = "Never"; // Default value for new users
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getUserId() {
        return userId;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    // Setter for lastLogin
    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }
}
