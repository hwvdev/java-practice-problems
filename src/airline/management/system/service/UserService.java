package airline.management.system.service;

import airline.management.system.model.User;
import airline.management.system.repo.UserRepo;

public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void registerUser(User user) {
        userRepo.add(user);
    }

    public User getUser(String email) {
        return userRepo.getUserByEmail(email).orElseThrow(() -> new RuntimeException("User Not Found with given email"));
    }

    public User userByUserId(String userId) {
        return userRepo.getUserById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    public User userByEmail(String email) {
        return userRepo.getUserByEmail(email).orElseThrow(() -> new RuntimeException("user does exist for given email"));
    }
}
