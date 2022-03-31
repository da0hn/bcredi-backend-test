package br.com.gabriel.analyzer.events;

import java.time.LocalDateTime;

public record EventMetadata(
  String id,
  EventSchema schema,
  EventAction action,
  LocalDateTime timestamp
) {
}
