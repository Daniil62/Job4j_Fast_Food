package ru.jod4j.kitchen.component;

import org.springframework.kafka.core.KafkaTemplate;

public interface MessageDelay<T> {

    void send(long seconds, KafkaTemplate<?, T> template, String topic, T value);
}
