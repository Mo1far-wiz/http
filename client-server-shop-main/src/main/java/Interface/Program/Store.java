package Interface.Program;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Store {
    public ArrayList<Group> groups = new ArrayList<>(); //список груп товарів

    public ArrayList<Product> searchedProducts = new ArrayList<>();

    public ArrayList<Product> findProducts() {
        return searchedProducts;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public Group getGroup(String name) {
        for (Group group : groups) {
            if (Objects.equals(group.getName(), name)) return group;
        }
        return null;
    }

    public Product getProduct(String name) {
        for (Group group : groups) {
            for (Product product : group.getProducts()) {
                if (Objects.equals(product.getName(), name)) return product;
            }
        }
        return null;
    }
}