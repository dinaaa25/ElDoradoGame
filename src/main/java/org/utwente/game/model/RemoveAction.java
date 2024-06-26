package org.utwente.game.model;

import org.utwente.market.model.Card;
import org.utwente.market.model.Resource;
import org.utwente.player.model.Player;
import org.utwente.util.ValidationResult;

public class RemoveAction extends Action {

  public RemoveAction(Player player, Resource resource) {
    super(player, resource);
  }

  @Override
  public void execute() {
    this.player.removeCardFromGame((Card) this.getResource());
  }

  @Override
  public ValidationResult validate() {
    return this.getResource() instanceof Card ? new ValidationResult(true, "you can remove any card from the game.")
        : new ValidationResult(false, "cannot remove coins this way.");
  }

  @Override
  public void discard() {
    // nothing to discard.
  }

}
