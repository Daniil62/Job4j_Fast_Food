package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class Supply {

    private Integer id;
    private Supplier supplier;
    private Product product;
    private LocalDate deliveryTime;
    private boolean isActive;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Supply)) {
            return false;
        }
        Supply supply = (Supply) o;
        return Objects.equals(id, supply.id)
                && Objects.equals(supplier, supply.supplier)
                && Objects.equals(product, supply.product);
    }

    @Override
    public int hashCode() {
        return (Objects.hashCode(id) * 31 + Objects.hashCode(supplier))
                * 31 + Objects.hashCode(product);
    }
}
