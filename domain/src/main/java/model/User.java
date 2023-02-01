package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class User {

    private Integer id;
    private String name;
    private Address address;
    private String phone;
    private String email;
    private String password;
    private int rating;

    public User(String name, Address address, String phone, String email, String password) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id)
                && Objects.equals(phone, user.phone)
                && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return (Objects.hash(id) * 31 + Objects.hashCode(phone))
                * 31 + Objects.hashCode(email);
    }
}
