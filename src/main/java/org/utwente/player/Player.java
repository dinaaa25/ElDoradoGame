package org.utwente.player;

import org.utwente.Board.Blockade.Blockade;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<Blockade> blockades;

    public Player(String name){
        this.name = name;
        this.blockades = new ArrayList<Blockade>();
    }

    public String getName() {
        return this.name;
    }

    public void addBlockade(Blockade b){
        this.blockades.add(b);
    }

    public int getBlockadeCount(){
        return blockades.size();
    }

    public Blockade getMaxBlockade() {
        int maxPower = 0;
        Blockade maxBlockade = null;
        for(Blockade b : blockades) {
            int currentPower = b.getPower();
            if(maxPower < currentPower){
                maxPower = currentPower;
                maxBlockade = b;
            }
        }
        return maxBlockade;
    }

    @Override
    public boolean equals(Object p) {
        if(p instanceof Player) {
            Player elPlayer = (Player)p;
            return this.getName().equals(elPlayer.getName());
        }
        return false;
    }


}
