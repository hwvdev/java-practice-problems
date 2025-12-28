package airline.management.system.repo;

import airline.management.system.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepo {
    private final Map<String, User> userMap = new ConcurrentHashMap<>();

    public User getUser(String email) {
        return userMap.get(email);
    }

    public void registerUser(User user) {
        userMap.putIfAbsent(user.getEmail(), user);
    }

}
