package org.utwente.game.model;

import org.utwente.market.model.Resource;
import org.utwente.util.event.EventType;

public class ScientistEffectPhase extends EffectPhase {

    public ScientistEffectPhase(Resource resource) {
        super(resource);
    }

    @Override
    protected void defineSteps() {
        steps.put(EventType.ScientistStep1, false);
        steps.put(EventType.ScientistStep2, false);

        mandatoryStepsOrder.add(EventType.ScientistStep1);

        allStepsOrder.add(EventType.ScientistStep1);
        allStepsOrder.add(EventType.ScientistStep2);

        effectPhaseEnum = EffectPhaseEnum.Scientist;
    }
}