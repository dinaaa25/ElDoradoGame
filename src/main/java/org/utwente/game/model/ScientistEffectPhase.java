package org.utwente.game.model;

import org.utwente.market.model.Resource;
import org.utwente.player.model.Player;
import org.utwente.util.event.EventType;

public class ScientistEffectPhase extends EffectPhase {

    public ScientistEffectPhase(Resource resource, Player player) {
        super(resource, player);
        effectPhaseEnum = EffectPhaseEnum.Scientist;
    }

    @Override
    protected void defineSteps() {
        this.createMandatoryStep(EventType.ScientistStep1, 0, "draw card", "1. Draw an additional card");
        this.createOptionalStep(EventType.ScientistStep2, 1,
                "Remove selected card", "2. (Optional) Select 1 card and then remove it from the game");
    }
}