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
}
