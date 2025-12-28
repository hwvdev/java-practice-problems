package airline.management.system.service;

import airline.management.system.model.User;
import airline.management.system.repo.UserRepo;

public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void registerUser(User user) {
        userRepo.registerUser(user);
    }

    public User getUser(String email) {
        return userRepo.getUser(email);
    }
}
