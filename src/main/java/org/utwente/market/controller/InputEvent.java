package org.utwente.market.controller;

import java.awt.event.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputEvent extends ActionEvent {
  private String input;

  public InputEvent(String input) {
    super(input, RESERVED_ID_MAX, input);
    this.input = input;
  }
}
