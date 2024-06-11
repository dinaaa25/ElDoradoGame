package org.utwente.util.event;

import java.util.function.Consumer;
import java.util.*;

// Publisher
public class EventManager {

    private static EventManager instance;
    private Map<EventType, List<Consumer<String>>> subscribers;

    private EventManager() {
        this.subscribers = new HashMap<>();
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
    }

    public void subscribe(Consumer<String> subscriber) {
        for (List<Consumer<String>> list : subscribers.values()) {
            list.add(subscriber);
        }
    }

    public void subscribe(Consumer<String> subscriber, EventType event) {
        List<Consumer<String>> eventConsumers = subscribers.get(event);
        eventConsumers.add(subscriber);
        subscribers.put(event, eventConsumers);
    }

    public void unsubscribe(Consumer<String> subscriber) {
        for (List<Consumer<String>> list : subscribers.values()) {
            list.remove(subscriber);
        }
    }

    public void unsubscribe(Consumer<String> subscriber, EventType event) {
        List<Consumer<String>> eventConsumers = subscribers.get(event);
        eventConsumers.remove(subscriber);
        subscribers.put(event, eventConsumers);
    }

    public void notifying(EventType event, String data) {
        List<Consumer<String>> eventSubscribers = subscribers.get(event);

        for (Consumer<String> subscriber : eventSubscribers) {
            subscriber.accept(data);
        }
    }

    public void notifying(EventType event) {
        this.notifying(event, "");
    }

    public List<Consumer<String>> getSubscribers() {
        return this.subscribers.values().stream().flatMap(subList -> subList.stream()).toList();
    }

}
