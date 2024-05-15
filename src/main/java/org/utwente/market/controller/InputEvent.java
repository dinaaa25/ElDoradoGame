package org.utwente.market.controller;

import org.utwente.util.Event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputEvent implements Event {
  private String input;

  public InputEvent(String input) {
    this.input = input;
  }
}
