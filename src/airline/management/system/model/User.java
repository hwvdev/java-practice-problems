package airline.management.system.model;

import java.util.Objects;
import java.util.UUID;

public final class User {
    private final String userId;
    private final String email;
    private final String name;
    private final UserType userType;

    public User(String email, String name, UserType userType) {
        this.userId = UUID.randomUUID().toString();
        this.email = email;
        this.name = name;
        this.userType = userType;
    }

    public User(String userId, String email, String name, UserType userType) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.userType = userType;
    }

    public User updateEmail(String email) {
        return new User(this.userId, email, this.name, this.userType);
    }

    public String getEmail() {
        return email;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", userType=" + userType +
                '}';
    }
}
