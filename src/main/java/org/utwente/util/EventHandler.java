package org.utwente.util;

public interface EventHandler<T extends Event> {
  public void handle(T event);
}
