package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class Product {

    private Integer id;
    private String name;
    private double price;
    private Supplier supplier;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(id, product.id)
                && Double.compare(product.price, price) == 0
                && Objects.equals(name, product.name)
                && Objects.equals(supplier, product.supplier);
    }

    @Override
    public int hashCode() {
        return ((Objects.hashCode(id) * 31 + Objects.hashCode(name))
                * 31 + Objects.hashCode(price)) * 31 + Objects.hashCode(supplier);
    }
}
