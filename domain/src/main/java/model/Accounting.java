package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class Accounting {

    private Integer id;
    private double cost;
    private double income;
    private LocalDate from;
    private LocalDate before;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Accounting)) {
            return false;
        }
        Accounting that = (Accounting) o;
        return Objects.equals(id, that.id)
                && Objects.equals(from, that.from)
                && Objects.equals(before, that.before);
    }

    @Override
    public int hashCode() {
        return (Objects.hashCode(id) * 31 + Objects.hashCode(from))
                * 31 + Objects.hashCode(before);
    }
}