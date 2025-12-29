package airline.management.system.model;

import java.util.Objects;

public final class User {
    private final String userId;
    private final String email;
    private final String name;
    private final UserType userType;

    enum UserType {PASSENGER, CREW, PILOT}

    public User(String userId, String email, String name, UserType userType) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.userType = userType;
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
}
