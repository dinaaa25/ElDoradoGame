package org.utwente.Tile;

import lombok.Getter;
import org.utwente.market.model.PowerType;
import org.utwente.util.ImageLoadable;

import java.util.List;

@Getter
public enum TileType implements ImageLoadable {

    Machete(List.of(PowerType.Machete, PowerType.Wild), new PowerRange(1, 3)),
    Paddle(List.of(PowerType.Paddle, PowerType.Wild), new PowerRange(1, 4)),
    Coin(List.of(PowerType.Coin, PowerType.Wild), new PowerRange(1, 4)),
    Basecamp(List.of(PowerType.Machete, PowerType.Paddle, PowerType.Coin, PowerType.Wild, PowerType.Effect), new PowerRange(1, 3)),
    Discard(List.of(PowerType.Machete, PowerType.Paddle, PowerType.Coin, PowerType.Wild, PowerType.Effect), new PowerRange(1, 3)),
    Mountain(List.of(), new PowerRange(0)),
    Cave(List.of(), new PowerRange(0)),
    ElDorado(List.of(), new PowerRange(0)),
    Start(List.of(), new PowerRange(0));

    final List<PowerType> powerTypeList;
    private final PowerRange powerRange;

    TileType(List<PowerType> powerTypeList, PowerRange powerRange) {
        this.powerTypeList = powerTypeList;
        this.powerRange = powerRange;
    }

}
