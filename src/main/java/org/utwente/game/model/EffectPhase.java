package org.utwente.game.model;

import lombok.Getter;
import org.utwente.market.model.Resource;
import org.utwente.util.event.EventType;

import java.util.*;

public abstract class EffectPhase {
    protected Map<EventType, Boolean> steps = new LinkedHashMap<>();
    protected List<EventType> mandatoryStepsOrder = new ArrayList<>();
    protected List<EventType> allStepsOrder = new ArrayList<>();
    @Getter
    protected EffectPhaseEnum effectPhaseEnum;
    @Getter
    protected Resource resource;
    /**
     * You can use this variable to set buttons to active or not in the EffectPhase
     */
    private int currentStepIndex = -1; // Initialize to -1 to indicate no steps have been completed yet

    public EffectPhase(Resource resource) {
        this.resource = resource;
        defineSteps();
    }

    /**
     * Use this method to define all the EventTypes, that have to be done and their order
     */
    protected abstract void defineSteps();

    /**
     * @param step defines the EventType that has to be done in the step
     * @throws IllegalArgumentException if step is not done in correct order
     */
    public void completeStep(EventType step) {
        if (!steps.containsKey(step)) {
            throw new IllegalArgumentException("Step not defined: " + step);
        }

        int stepIndex = allStepsOrder.indexOf(step);
        if (stepIndex == -1) {
            throw new IllegalArgumentException("Step order not defined: " + step);
        }

        if (stepIndex != currentStepIndex + 1) {
            throw new IllegalStateException("Steps must be completed in order");
        }

        currentStepIndex = stepIndex;
        steps.put(step, true);
    }

    /**
     * @return the current step to be completed
     */
    public EventType getCurrentStep() {
        if (currentStepIndex + 1 < allStepsOrder.size()) {
            return allStepsOrder.get(currentStepIndex + 1);
        }
        return null; // All steps are completed
    }

    /**
     * @return whether all indicated mandatory steps of the EffectPhase have been done.
     */
    public boolean allMandatoryStepsCompleted() {
        for (EventType step : mandatoryStepsOrder) {
            if (!steps.getOrDefault(step, false)) {
                return false;
            }
        }
        return true;
    }
}