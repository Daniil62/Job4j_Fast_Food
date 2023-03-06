package ru.job4j.admin.service.ingredient;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.admin.domain.Ingredient;

import java.util.List;

@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final String url = "http://localhost:8081/";
    private final RestTemplate restTemplate;

    @Override
    public List<Ingredient> getAll() {
        return restTemplate
                .exchange(url + "getAllIngredients", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Ingredient>>() {
                }).getBody();
    }

    @Override
    public Ingredient add(Ingredient ingredient) {
        return restTemplate
                .postForEntity(url + "addIngredient",
                        ingredient, Ingredient.class).getBody();
    }

    @Override
    public boolean delete(int id) {
        return restTemplate
                .exchange(url + "deleteIngredient/" + id,
                        HttpMethod.DELETE, HttpEntity.EMPTY, Void.class)
                .getStatusCode() != HttpStatus.NOT_FOUND;
    }
}