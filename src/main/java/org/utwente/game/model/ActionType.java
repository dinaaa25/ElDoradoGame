package org.utwente.game.model;

public enum ActionType {
    PlayCards(0),
    BuyCards(1),
    DiscardCards(2);


    int order;
    ActionType(int order) {
        this.order = order;
    }

    public int getValue() {
        return this.order;
    }

    public ActionType getActionTypeByOrder(int order) throws Exception {
        for (ActionType type : ActionType.values()) {
            if (type.getValue() == order) {
                return type;
            }
        }
        throw new Exception("No actionType found!");
    }



}
