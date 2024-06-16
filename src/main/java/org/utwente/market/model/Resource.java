package org.utwente.market.model;

public interface Resource {

    public int getPower();
    public PowerType getType();
    public double getValue();
    public void removePower(int toBeRemoved) throws CardPowerException;

}
