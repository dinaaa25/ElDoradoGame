package org.utwente.market.model;

public interface Resource {

    /**
     * @return the original power of the Resource
     */
    public int getPower();
    public PowerType getType();

    /**
     * @return the value the card holds in the Market purchase action
     */
    public double getValue();

    /**
     * @param toBeRemoved specifies the amount of power that has to be deducted from the Resource for an action
     * @throws CardPowerException when there is not enough power left in the Resource to deduct fully
     */
    public void removePower(int toBeRemoved) throws CardPowerException;

    /**
     * @return the amount of power that is already used of the Resource
     */
    public int getConsumedPower();

    /**
     * @return the amount of power that is still left in the Resource
     */
    public int remainingPower();
}
