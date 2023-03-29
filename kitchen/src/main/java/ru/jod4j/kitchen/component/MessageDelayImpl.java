package ru.jod4j.kitchen.component;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

@Component
public class MessageDelayImpl<T> implements MessageDelay<T> {

    private final ExecutorService service = Executors.newSingleThreadExecutor();

    @Override
    public void send(long seconds, KafkaTemplate<?, T> template, String topic, T value) {
        FutureTask<?> task = new FutureTask<>(() -> template.send(topic, value));
        try {
            TimeUnit.SECONDS.sleep(seconds);
            task.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.submit(task);
    }
}