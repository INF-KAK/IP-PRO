package application.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void add(User user);
    void remove(User user);
    void update(User user) throws Exception;
    Optional<User> getByName(String name);
    List<User> getAllUsers();
}
