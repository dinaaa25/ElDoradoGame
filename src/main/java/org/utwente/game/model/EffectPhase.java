package org.utwente.game.model;

import lombok.Getter;

import org.utwente.CaveCoin.CaveCoin;
import org.utwente.market.model.Card;
import org.utwente.market.model.Resource;
import org.utwente.player.model.Player;
import org.utwente.util.event.EventType;

import java.util.*;

@Getter
public abstract class EffectPhase {
    protected Map<EventType, EffectStep> steps = new LinkedHashMap<>();
    protected EffectPhaseEnum effectPhaseEnum;
    protected Resource resource;
    protected Player player;
    /**
     * You can use this variable to set buttons to active or not in the EffectPhase
     */
    private int currentStepIndex = -1; // Initialize to -1 to indicate no steps have been completed yet

    public EffectPhase(Resource resource, Player player) {
        this.resource = resource;
        this.player = player;
        defineSteps();
    }

    /**
     * Use this method to define all the EventTypes, that have to be done and their
     * order
     */
    protected abstract void defineSteps();

    protected void createOptionalStep(EventType eventType, int order, String buttonText, String stepLabel) {
        this.steps.put(eventType, new EffectStep(false, false, order, buttonText, stepLabel));
    }

    protected void createMandatoryStep(EventType eventType, int order, String buttonText, String stepLabel) {
        this.steps.put(eventType, new EffectStep(false, true, order, buttonText, stepLabel));
    }

    /**
     * @param step defines the EventType that has to be done in the step
     * @throws IllegalArgumentException if step is not done in correct order
     */
    public void completeStep(EventType step) {
        if (!steps.containsKey(step)) {
            throw new IllegalArgumentException("Step not defined: " + step);
        }

        EffectStep effectStep = steps.get(step);
        int stepIndex = effectStep.getOrder();
        if (stepIndex == -1) {
            throw new IllegalArgumentException("Step order not defined: " + step);
        }

        if (stepIndex != currentStepIndex + 1) {
            throw new IllegalStateException("Steps must be completed in order");
        }

        currentStepIndex = stepIndex;
        effectStep.setCompleted(true);
        // steps.put(step, true);
    }

    /**
     * @return the current step to be completed
     */
    public EventType getCurrentStep() {
        return this.steps.entrySet().stream().filter(e -> e.getValue().getOrder() == this.currentStepIndex + 1)
                .map(e -> e.getKey()).findFirst().orElse(null);
    }

    /**
     * @return whether all indicated mandatory steps of the EffectPhase have been
     *         done.
     */
    public boolean allMandatoryStepsCompleted() {
        return this.steps.values().stream().filter(e -> !e.isCompleted() && e.isMandatory()).count() == 0;
    }

    public void discardEffectResource() {
        if (resource instanceof Card card) {
            if (card.getCardType().oneTimeUse) {
                this.player.removeCardFromGame(card);

            } else {
                this.player.discardResource(card);
            }
        } else if (resource instanceof CaveCoin caveCoin) {

        }
    }
}