package model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
public class Courier {

    private Integer id;
    private String name;
    private Location location;
    private Set<Order> orders = new HashSet<>();

    public Courier(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public Courier(int id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public boolean addOrder(Order order) {
        return orders.add(order);
    }

    public boolean removeOrder(Order order) {
        return orders.remove(order);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Courier)) {
            return false;
        }
        Courier courier = (Courier) o;
        return Objects.equals(id, courier.id) && Objects.equals(name, courier.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name) * 31 + Objects.hashCode(id);
    }
}