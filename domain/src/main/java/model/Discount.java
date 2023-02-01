package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class Discount {

    private Integer id;
    private LocalDateTime begin;
    private LocalDateTime before;
    private int percent;
    private double value;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Discount)) {
            return false;
        }
        Discount discount = (Discount) o;
        return percent == discount.percent
                && Double.compare(discount.value, value) == 0
                && Objects.equals(begin, discount.begin)
                && Objects.equals(before, discount.before);
    }

    @Override
    public int hashCode() {
        return ((Objects.hashCode(begin) * 31 + Objects.hashCode(before))
                * 31 + Objects.hashCode(percent)) * 31 + Objects.hashCode(value);
    }
}