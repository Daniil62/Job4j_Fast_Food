package ru.job4j.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Expose
    private Integer id;
    @Expose
    private String street;
    @Expose
    private String home;
    @Expose
    private String apartment;

    public Address(String street, String home, String apartment) {
        this.street = street;
        this.home = home;
        this.apartment = apartment;
    }

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