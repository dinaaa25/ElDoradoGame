package org.utwente.game.model;


import org.utwente.market.model.Resource;
import org.utwente.player.model.Player;

import java.util.List;

public abstract class Action{

    protected Player player;

    /***
     * for the buy action these are all the resources you want to spend when doing the specific buy action
     * for the move action this list contains only one resource to spend on one move
     * for discard action resources contains all cards you want to throw away
     */
    protected List<Resource> resources;

    public Action(Player player, Resource resource) {
        this(player, List.of(resource));
    }

    public Action(Player player, List<Resource> resourceList){
        this.player = player;
        this.resources = resourceList;
    }

    /**
     * is only NOT for the buyaction since buy action has many resources to make a buy
     * @return null if isEmpty() or resource.get(0)
     */
    public Resource getResource() {
        if(resources == null || resources.isEmpty()) {
            return null;
        }
        return this.resources.get(0);
    }

    public abstract void execute();

    public abstract boolean validate();

    public void validateExecute() {
        if(validate()) {
            execute();
            discard();
        }
    }

    public abstract void discard();

}

