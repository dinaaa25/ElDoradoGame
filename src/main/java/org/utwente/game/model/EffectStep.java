package org.utwente.game.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EffectStep {
  private boolean completed;
  private boolean mandatory;
  private int order;
  private String buttonText;
  private String stepLabel;

  @Override
  public String toString() {
    return "EffectStep{" +
            "completed=" + completed +
            ", mandatory=" + mandatory +
            ", order=" + order +
            ", buttonText='" + buttonText + '\'' +
            ", stepLabel='" + stepLabel + '\'' +
            '}';
  }
}
