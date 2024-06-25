package org.utwente.game.model;

import org.utwente.CaveCoin.CaveCoin;
import org.utwente.CaveCoin.CaveCoinType;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Resource;
import org.utwente.player.model.CardPile;
import org.utwente.player.model.Player;
import org.utwente.util.ValidationResult;

import java.util.List;

public class DrawAction extends Action {

    public DrawAction(Player player, Resource resource) {
        super(player, resource);
    }

    @Override
    public void execute() {
        CardPile drawPile = player.getDrawPile();
        CardPile playPile = player.getPlayPile();
        int power = this.getResource().getPower();
        playPile.addAll(drawPile.draw(power));
    }

    @Override
    public ValidationResult validate() {
        Resource resource = this.getResource();
        if (this.getResource() instanceof Card) {
            Card card = (Card) resource;
            boolean cardAllowed = List.of(CardType.Kartograph, CardType.Kompass, CardType.Wissenschaftlerin,
                    CardType.Reisende).contains(card.getCardType());
            return new ValidationResult(cardAllowed, cardAllowed ? "" : "Card not allowed for draw action.");
        }
        if (this.getResource() instanceof CaveCoin) {
            CaveCoin caveCoin = (CaveCoin) resource;
            boolean coinAllowed = caveCoin.caveCoinType() == CaveCoinType.Draw;
            return new ValidationResult(coinAllowed, coinAllowed ? "" : "Coin not allowed for draw action.");
        }
        return new ValidationResult(false, "Not a Coin or Card.");
    }

    @Override
    public void discard() {
        // since this is a special card or coin.
        // it should be discarded in the end of the EffectPhase.
    }
}
