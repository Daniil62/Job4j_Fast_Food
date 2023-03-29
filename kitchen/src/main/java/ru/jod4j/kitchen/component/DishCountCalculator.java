package ru.jod4j.kitchen.component;

import org.springframework.stereotype.Component;
import ru.jod4j.kitchen.domain.dto.DishDTO;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class DishCountCalculator implements CountCalculator<DishDTO> {

    @Override
    public Map<DishDTO, Integer> calculate(Collection<DishDTO> list) {
        Map<DishDTO, Integer> resultMap = new HashMap<>();
        list.forEach(e -> resultMap.put(e, resultMap.getOrDefault(e, 0) + 1));
        return resultMap;
    }
}
