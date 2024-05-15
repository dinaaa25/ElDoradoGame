package org.utwente.util;

public interface EventHandler<T> {
  public void handle(T event);
}
