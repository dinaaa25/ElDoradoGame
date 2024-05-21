package org.utwente.game;


import org.utwente.market.model.Resource;
import org.utwente.player.Player;

public abstract class Action{

    private Player player;
    private Resource resource;

    public Action(Player player, Resource resource) {
        this.player = player;
        this.resource = resource;
    }

    abstract void execute();

    abstract void validate();


}

