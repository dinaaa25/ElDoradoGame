package org.utwente.game.view;

import org.utwente.game.controller.Subscriber;

import java.util.ArrayList;
import java.util.List;

// Publisher
public class EventManager {

    private static EventManager single_eventManager = new EventManager();

    List<Subscriber> subscribers;

    private EventManager() {
        subscribers = new ArrayList<Subscriber>();
    }

    public static EventManager getEventManager() {
        return single_eventManager;
    }

    public void resetSubscribers() {
        subscribers.clear();
    }

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public List<Subscriber> getSubscribers() {
        return this.subscribers;
    }


    public void notifying(EventType event) {
        for(Subscriber subscriber : subscribers) {
            subscriber.update(event);
        }
    }

    public static enum EventType {
        StartGame,
        EndGame,
        PlayCards,
        BuyCards,
        DiscardCards
    }

}
