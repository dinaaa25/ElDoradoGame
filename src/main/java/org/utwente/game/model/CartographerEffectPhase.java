package org.utwente.game.model;

import org.utwente.market.model.Resource;
import org.utwente.player.model.Player;
import org.utwente.util.event.EventType;

public class CartographerEffectPhase extends EffectPhase {

  public CartographerEffectPhase(Resource resource, Player player) {
    super(resource, player);
    this.effectPhaseEnum = EffectPhaseEnum.Cartographer;
  }

  @Override
  protected void defineSteps() {
    this.createMandatoryStep(EventType.CartographerEvent, 0, "Draw cards", "You can draw two cards.");
  }

}
