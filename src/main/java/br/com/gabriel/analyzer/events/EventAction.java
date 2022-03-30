package br.com.gabriel.analyzer.events;

import java.util.Locale;

public enum EventAction {

  CREATED,
  ADDED,
  UPDATED,
  DELETED,
  REMOVED;

  public String asRaw() {
    return this.name().toLowerCase(Locale.ROOT);
  }

}
