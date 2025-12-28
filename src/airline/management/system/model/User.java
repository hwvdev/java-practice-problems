package airline.management.system.model;

public final class User {
    private final String email;
    private final String name;
    private final UserType userType;

    enum UserType {PASSENGER, CREW, PILOT}

    public User(String email, String name, UserType userType) {
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

}
