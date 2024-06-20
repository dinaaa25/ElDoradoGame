package org.utwente.util.event;

import java.util.function.Consumer;
import java.util.*;

// Publisher
public class EventManager {

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
        for (List<Consumer<Event>> list : subscribers.values()) {
            list.add(subscriber);
        }
    }

    public void subscribe(Consumer<Event> subscriber, EventType event) {
        List<Consumer<Event>> eventConsumers = subscribers.get(event);
        eventConsumers.add(subscriber);
        subscribers.put(event, eventConsumers);
    }

    public void unsubscribe(Consumer<Event> subscriber) {
        for (List<Consumer<Event>> list : subscribers.values()) {
            list.remove(subscriber);
        }
    }

    public void unsubscribe(Consumer<Event> subscriber, EventType event) {
        List<Consumer<Event>> eventConsumers = subscribers.get(event);
        eventConsumers.remove(subscriber);
        subscribers.put(event, eventConsumers);
    }

    public void notifying(EventType event, Event data) {
        List<Consumer<Event>> eventSubscribers = subscribers.get(event);

        for (Consumer<Event> subscriber : eventSubscribers) {
            subscriber.accept(data);
        }
    }

    public void notifying(EventType event) {
        this.notifying(event, new Event() {});
    }

    public List<Consumer<Event>> getSubscribers() {
        return this.subscribers.values().stream().flatMap(List::stream).toList();
    }
}