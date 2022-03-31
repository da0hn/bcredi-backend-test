package br.com.gabriel.analyzer.events;

import java.util.Locale;


public enum EventSchema {

  WARRANTY,
  PROPONENT,
  PROPOSAL;

  public String asRaw() {
    return this.name().toLowerCase(Locale.ROOT);
  }


}
