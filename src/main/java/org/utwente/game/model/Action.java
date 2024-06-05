package org.utwente.game.model;


import org.utwente.market.model.Resource;
import org.utwente.player.Player;

public abstract class Action{

    protected Player player;
    protected Resource resource;

    public Action(Player player, Resource resource) {
        this.player = player;
        this.resource = resource;
    }

    public abstract void execute();

    public abstract boolean validate();


}

