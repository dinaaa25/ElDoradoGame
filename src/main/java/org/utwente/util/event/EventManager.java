package org.utwente.util.event;

import java.util.function.Consumer;
import java.util.*;

// Publisher
public class EventManager {

    private static EventManager instance;
    private Map<EventType, List<Consumer<Object>>> subscribers;

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
        return this.subscribers.keySet().stream().toList();
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

    public void subscribe(Consumer<Object> subscriber) {
        for (List<Consumer<Object>> list : subscribers.values()) {
            list.add(subscriber);
        }
    }

    public void subscribe(Consumer<Object> subscriber, EventType event) {
        List<Consumer<Object>> eventConsumers = subscribers.get(event);
        eventConsumers.add(subscriber);
        subscribers.put(event, eventConsumers);
    }

    public void unsubscribe(Consumer<Object> subscriber) {
        for (List<Consumer<Object>> list : subscribers.values()) {
            list.remove(subscriber);
        }
    }

    public void unsubscribe(Consumer<Object> subscriber, EventType event) {
        List<Consumer<Object>> eventConsumers = subscribers.get(event);
        eventConsumers.remove(subscriber);
        subscribers.put(event, eventConsumers);
    }

    public void notifying(EventType event, Object data) {
        List<Consumer<Object>> eventSubscribers = subscribers.get(event);

        for (Consumer<Object> subscriber : eventSubscribers) {
            subscriber.accept(data);
        }
    }

    public void notifying(EventType event) {
        this.notifying(event, "");
    }

    public List<Consumer<Object>> getSubscribers() {
        return this.subscribers.values().stream().flatMap(subList -> subList.stream()).toList();
    }

}
