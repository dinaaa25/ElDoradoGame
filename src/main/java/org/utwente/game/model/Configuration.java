package org.utwente.game.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Configuration {
  public boolean gui;
  public String loggingLevel;

  static Configuration _instance;

  public static Configuration getInstance() {
    if (_instance == null) {
      _instance = new Configuration(true, "ALL");
    }

    return _instance;
  }

  @JsonCreator
  private Configuration(@JsonProperty boolean gui, @JsonProperty String loggingLevel) {
    this.gui = gui;
    this.loggingLevel = loggingLevel;
  }
}
