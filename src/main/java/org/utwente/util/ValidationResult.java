package org.utwente.util;

public record ValidationResult(boolean status, String message) {
  public boolean getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }
}
