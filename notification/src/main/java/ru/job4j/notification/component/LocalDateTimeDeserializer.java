package ru.job4j.notification.component;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT,
                                     JsonDeserializationContext context) {
        return LocalDateTime.parse(json.getAsString(),
            DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm").withLocale(Locale.ENGLISH));
    }
}