package ru.job4j.admin.service.dish;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.admin.domain.Dish;
import ru.job4j.admin.domain.Ingredient;
import ru.job4j.admin.domain.dto.RawDish;

@Service
public class DishServiceImpl implements DishService {

    private final String url = "http://localhost:8081/";
    private final RestTemplate restTemplate;

    public DishServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Iterable<Dish> getAll() {
        return restTemplate
                .exchange(url + "getAllDishes", HttpMethod.GET,
                null, new ParameterizedTypeReference<Iterable<Dish>>() {
        }).getBody();
    }

    @Override
    public Dish getById(int id) {
        return restTemplate
                .getForEntity(String.format("%s%s?id=%d",
                        url, "getDishById", id), Dish.class)
                .getBody();
    }

    @Override
    public Dish add(Dish dish) {
        return restTemplate
                .postForEntity(url + "addDish", dish, Dish.class)
                .getBody();
    }

    @Override
    public boolean delete(int id) {
        return restTemplate
                .exchange(url + "deleteDish/" + id, HttpMethod.DELETE,
                        HttpEntity.EMPTY, Void.class)
                .getStatusCode() != HttpStatus.NOT_FOUND;
    }

    @Override
    public Dish convertRawDishToDish(RawDish rawDish) {
        Dish result = new Dish();
        if (rawDish != null) {
            result.setName(rawDish.getName());
            for (String arg : rawDish.getIngredients()) {
                String[] args = arg.split("-");
                result.addIngredient(new Ingredient(Integer.parseInt(args[0]), args[1]));
            }
        }
        return result;
    }
}