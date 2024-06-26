package org.utwente.game.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Configuration {
  public boolean gui;
  public String loggingLevel;
  public boolean freeMarket;
  public boolean xray; // Added xray variable

  static Configuration _instance;

  private static Configuration loadConfigurationFromFile() {
    ObjectMapper mapper = new ObjectMapper();
    try {
      File file = Paths.get("src", "main", "resources", "config.json").toFile();
      System.out.println(file.getAbsolutePath());
      return mapper.readValue(file, Configuration.class);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static Configuration getInstance() {
    if (_instance == null) {
      _instance = loadConfigurationFromFile();
      if (_instance == null) {
        _instance = new Configuration(true, "ALL", false, false);
      }
    }

    return _instance;
  }

  @JsonCreator
  private Configuration(
          @JsonProperty("gui") boolean gui,
          @JsonProperty("loggingLevel") String loggingLevel,
          @JsonProperty("freeMarket") boolean freeMarket,
          @JsonProperty("xray") boolean xray) { // Updated constructor
    this.gui = gui;
    this.loggingLevel = loggingLevel;
    this.freeMarket = freeMarket;
    this.xray = xray;
  }
}
