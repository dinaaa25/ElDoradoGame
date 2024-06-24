package org.utwente.util.event;

import org.utwente.market.model.Card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlayCardEvent extends Event {
    Card card;

    @Override
    public String toString() {
        return "PlayCardEvent{" +
                "card=" + card +
                '}';
    }
}
