package org.utwente.game.model;

import lombok.Getter;
import lombok.Setter;
import org.utwente.market.model.Card;
import org.utwente.market.model.PowerType;
import org.utwente.market.model.Resource;
import org.utwente.player.model.Player;
import org.utwente.util.event.EventType;

@Getter
@Setter
public class CaveCoinSymbolEffectPhase extends EffectPhase {
    Card cardToSymbolChange;
    PowerType powerTypeToChangeTo;

    public CaveCoinSymbolEffectPhase(Resource resource, Player player) {
        super(resource, player);
        this.effectPhaseEnum = EffectPhaseEnum.CaveCoinSymbol;
    }

    @Override
    protected void defineSteps() {
        this.createMandatoryStep(EventType.PlayCards, 0, "Select card", "Select card that needs to be symbol swapped");
        this.createMandatoryStep(EventType.CaveCoinSymbolSelectType, 1, "Select type", "Select the type to which the selected card has to be swapped");
    }

    @Override
    public String toString() {
        return "CaveCoinSymbolEffectPhase{" +
                "steps=" + steps +
                ", cardToSymbolChange=" + cardToSymbolChange +
                ", powerTypeToChangeTo=" + powerTypeToChangeTo +
                ", effectPhaseEnum=" + effectPhaseEnum +
                ", resource=" + resource +
                ", player=" + player +
                '}';
    }
}
