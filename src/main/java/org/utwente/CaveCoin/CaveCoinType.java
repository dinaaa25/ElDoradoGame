package org.utwente.CaveCoin;

import lombok.Getter;
import org.utwente.Tile.PowerRange;

@Getter
public enum CaveCoinType {
    Machete(new PowerRange(1, 3)),
    Paddle(new PowerRange(1, 2)),
    Coin(new PowerRange(1, 2)),
    Draw,
    Remove,
    Replace,
    DontRemove,
    PassThrough,
    Adjacent,
    Symbol;

    private final PowerRange powerRange;

    CaveCoinType(PowerRange powerRange) {
        this.powerRange = powerRange;
    }

    CaveCoinType() {
        this.powerRange = new PowerRange(1);
    }

}
