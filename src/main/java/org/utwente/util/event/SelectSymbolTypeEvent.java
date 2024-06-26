package org.utwente.util.event;

import org.utwente.market.model.Card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.utwente.market.model.PowerType;

@Getter
@Setter
@AllArgsConstructor
public class SelectSymbolTypeEvent extends Event {
    PowerType powerType;

    @Override
    public String toString() {
        return "SelectSymbolTypeEvent{" +
                "powerType=" + powerType +
                '}';
    }
}
