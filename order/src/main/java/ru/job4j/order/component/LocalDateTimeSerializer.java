package ru.job4j.order.component;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {

    @Override
    public JsonElement serialize(LocalDateTime localDateTime,
                                 Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(DateTimeFormatter
                .ofPattern("dd.MM.yyyy, HH:mm")
                .format(localDateTime));
    }
}