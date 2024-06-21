package org.utwente.CaveCoin;

import lombok.Getter;
import lombok.Setter;
import org.utwente.util.event.Event;

@Getter
@Setter
public class CaveCoinClickEvent extends Event {
    CaveCoin caveCoin;

    public CaveCoinClickEvent(CaveCoin caveCoin) {
        this.caveCoin = caveCoin;
    }
}
