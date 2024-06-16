package org.utwente.game.model;

import org.utwente.CaveCoin.CaveCoin;
import org.utwente.CaveCoin.CaveCoinType;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Resource;
import org.utwente.player.model.Pile;
import org.utwente.player.model.Player;

import java.util.List;

public class DrawAction extends Action {

    public DrawAction(Player player, Resource resource) {
        super(player, resource);
    }

    @Override
    public void execute() {
        Pile drawPile = player.getDrawPile();
        Pile playPile = player.getPlayPile();
        int power = this.getResource().getPower();
        playPile.addAll(drawPile.draw(power));
    }

    @Override
    public boolean validate() {
        Resource resource = this.getResource();
        if(this.getResource() instanceof Card) {
            Card card = (Card) resource;
            return List.of(CardType.Kartograph, CardType.Kompass, CardType.Wissenschaftlerin,
                    CardType.Reisende).contains(card.getCardType());
        }
        if(this.getResource() instanceof CaveCoin) {
            CaveCoin caveCoin = (CaveCoin) resource;
            return caveCoin.caveCoinType() == CaveCoinType.Draw;
        }
        return false;
    }

    @Override
    public void discard() {
        if(this.getResource() instanceof Card) {
            Card card = (Card) this.getResource();
            if((card.getCardType() == CardType.Kartograph) || (card.getCardType() == CardType.Wissenschaftlerin)) {
                Pile discardPile = player.getDrawPile();
                discardPile.add(card);
                Pile playPile = player.getPlayPile();
                playPile.remove(card);
            }
            Pile outOfGamePile = player.getOutOfGamePile();
            outOfGamePile.add(card);
            Pile playPile = player.getPlayPile();
            playPile.remove(card);
        }

    }
}
