package model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
public class Order {

    private Integer id;
    private User user;
    private Courier courier;
    private String status;
    private Set<Dish> dishList = new HashSet<>();

    public Order(User user, Courier courier, String status) {
        this.user = user;
        this.courier = courier;
        this.status = status;
    }

    public boolean addDish(Dish dish) {
        return dishList.add(dish);
    }

    public boolean removeDish(Dish dish) {
        return dishList.remove(dish);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(id, order.id)
                && Objects.equals(user, order.user)
                && Objects.equals(courier, order.courier)
                && Objects.equals(dishList, order.dishList);
    }

    @Override
    public int hashCode() {
        return ((Objects.hashCode(id) * 31 + Objects.hashCode(user))
                * 31 + Objects.hashCode(courier)) * 31 + Objects.hashCode(dishList);
    }
}