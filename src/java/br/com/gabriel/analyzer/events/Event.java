package br.com.gabriel.analyzer.events;

import java.time.ZonedDateTime;

public record Event(
  String id,
  String schema,
  EventAction action,
  ZonedDateTime timestamp
) {
}
