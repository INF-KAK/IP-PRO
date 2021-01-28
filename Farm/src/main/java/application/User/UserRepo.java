package application.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserRepo implements UserRepository{
        private List<User> users = new LinkedList<>();

        @Override
        public void add(User user) {
            users.add(user);
        }

        @Override
        public void remove(User user) {
            users.remove(user);
        }

        @Override
        public void update(User user) throws Exception {
            Optional<User> updatedUser = getByName(user.getName());

            if(updatedUser.isPresent()) {
                User newUser = User.create(updatedUser.get().getName(), updatedUser.get().getLastName(), user.getPhoneNumber(), user.getFields(), user.getRoles());

                users.set(users.indexOf(updatedUser.get()), newUser);
            }
        }

        @Override
        public Optional<User> getByName(String name) {
            return users.stream().filter(u -> u.getName().equals(name)).findFirst();
        }

        @Override
        public List<User> getAllUsers() {
            return users;
        }
}
