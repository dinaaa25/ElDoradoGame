package org.utwente.game.controller;

import org.utwente.game.view.EventManager;

public interface Subscriber {

    void update(EventManager.EventType event);

}
