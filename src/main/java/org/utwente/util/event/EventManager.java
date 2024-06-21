package org.utwente.util.event;

import java.util.function.Consumer;
import java.util.*;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

// Publisher
public class EventManager {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(EventManager.class);

    private static EventManager instance;
    private Map<EventType, List<Consumer<Event>>> subscribers;

    private EventManager() {
        this.subscribers = new HashMap<>();
        setup();
    }

    private void setup() {
        for (EventType event : EventType.values()) {
            this.subscribers.put(event, new ArrayList<>());
        }
    }

    public List<EventType> getEventTypes() {
        return new ArrayList<>(this.subscribers.keySet());
    }

    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }

        return instance;
    }

    public void resetSubscribers() {
        subscribers.clear();
        setup();
    }

    public void subscribe(Consumer<Event> subscriber) {
        logger.info("added subscriber for all events");
        for (List<Consumer<Event>> list : subscribers.values()) {
            list.add(subscriber);
        }
    }

    public void subscribe(Consumer<Event> subscriber, EventType event) {
        logger.info(String.format("added subscriber for %s", event.toString()));
        List<Consumer<Event>> eventConsumers = subscribers.get(event);
        eventConsumers.add(subscriber);
        subscribers.put(event, eventConsumers);
    }

    public void unsubscribe(Consumer<Event> subscriber) {
        logger.info("unsubscribed subscriber for all events");
        for (List<Consumer<Event>> list : subscribers.values()) {
            list.remove(subscriber);
        }
    }

    public void unsubscribe(Consumer<Event> subscriber, EventType event) {
        logger.info(String.format("unsubscribed subscriber for %s", event.toString()));
        List<Consumer<Event>> eventConsumers = subscribers.get(event);
        eventConsumers.remove(subscriber);
        subscribers.put(event, eventConsumers);
    }

    public void notifying(EventType event, Event data) {
        if (data instanceof EmptyEvent) {
            logger.info(String.format("Event(%s)", event.toString()));
        } else {
            logger.info(String.format("Event(%s) | Data: %s", event.toString(), data.toString()));
        }

        List<Consumer<Event>> eventSubscribers = subscribers.get(event);
        if (eventSubscribers != null) {
            for (Consumer<Event> subscriber : eventSubscribers) {
                subscriber.accept(data);
            }
        }
    }

    public void notifying(EventType event) {
        this.notifying(event, new EmptyEvent());
    }

    public List<Consumer<Event>> getSubscribers() {
        return this.subscribers.values().stream().flatMap(List::stream).toList();
    }
}