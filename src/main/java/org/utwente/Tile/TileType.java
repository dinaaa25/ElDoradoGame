package org.utwente.Tile;

import org.utwente.market.model.PowerType;

import java.util.List;

public enum TileType {

    Machete(List.of(PowerType.Machete, PowerType.Wild)),
    Paddle(List.of(PowerType.Paddle, PowerType.Wild)),
    Coin(List.of(PowerType.Coin, PowerType.Wild)),
    Basecamp(List.of(PowerType.Machete, PowerType.Paddle, PowerType.Coin, PowerType.Wild, PowerType.Effect)),
    Discard(List.of(PowerType.Machete, PowerType.Paddle, PowerType.Coin, PowerType.Wild, PowerType.Effect)),
    Mountain(List.of()),
    Cave(List.of()),
    ElDorado(List.of()),
    Start(List.of());

    List<PowerType> powerTypeList;

    TileType(List<PowerType> powerTypeList) {
        this.powerTypeList = powerTypeList;
    }

    public List<PowerType> getPowerTypeList() {
        return this.powerTypeList;
    }
}
