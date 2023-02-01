package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class Address {

    private Integer id;
    private String street;
    private String home;
    private String apartment;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Address)) {
            return false;
        }
        Address address = (Address) o;
        return Objects.equals(id, address.id)
                && Objects.equals(street, address.street)
                && Objects.equals(home, address.home)
                && Objects.equals(apartment, address.apartment);
    }

    @Override
    public int hashCode() {
        return ((Objects.hashCode(id) * 31 + Objects.hashCode(street))
                * 31 + Objects.hashCode(home)) * 31 + Objects.hashCode(apartment);
    }
}