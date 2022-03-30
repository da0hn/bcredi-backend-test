package br.com.gabriel.analyzer.events;

import java.time.ZonedDateTime;

public record EventMetadata(
  String id,
  String schema,
  EventAction action,
  ZonedDateTime timestamp
) {
}
