package application.Exhibition;

import application.User.User;

import java.util.List;

public interface ProductRepository {
    void buyProduct(User user, Exhibition exhibition);
}
