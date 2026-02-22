package airline.management.system.repo;

import airline.management.system.data.store.InMemoryStore;
import airline.management.system.exception.UserAlreadyExistsException;
import airline.management.system.model.User;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class UserRepo {
    private final Map<String, User> userByIdMap;

    public UserRepo(InMemoryStore store) {
        this.userByIdMap = store.userByIdMap;
    }

    public void add(User user) {
        userByIdMap.compute(user.getUserId(), (userId, existingUser) -> {
            if (existingUser!=null) {
                throw new UserAlreadyExistsException("User already exists");
            }
            return user;
        });
    }

    public void updateEmail(String userId, String email) {
        userByIdMap.compute(userId, (useId, existingUser) -> {
            if (existingUser==null)
                throw new RuntimeException("User does not exist");
            if (Objects.equals(email, existingUser.getEmail())) {
                throw new RuntimeException("Email already exists");
            }
            return new User(existingUser.getUserId(), email, existingUser.getName(), existingUser.getUserType());
        });
    }

    public Optional<User> getUserById(String userId) {
        return Optional.ofNullable(userByIdMap.get(userId));
    }

    public Optional<User> getUserByEmail(String email) {
        return userByIdMap.values()
                .stream()
                .filter(user -> Objects.equals(user.getEmail(), email)).findFirst();
    }

}
