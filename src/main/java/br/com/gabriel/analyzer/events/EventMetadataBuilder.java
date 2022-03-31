package br.com.gabriel.analyzer.events;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class EventMetadataBuilder {
  private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
  private String id;
  private String schema;
  private EventAction action;
  private LocalDateTime timestamp;

  private EventMetadataBuilder() {
  }

  public static EventMetadataBuilder anEventMetadata() {
    return new EventMetadataBuilder();
  }

  public void withId(String id) {
    this.id = id;
  }

  public void withSchema(String schema) {
    this.schema = schema;
  }


  public void withAction(String action) {
    this.action = EventAction.valueOf(action.toUpperCase(Locale.ROOT));
  }

  public void withTimestamp(String timestamp) {
    this.timestamp = LocalDateTime.parse(timestamp, formatter);
  }

  public String schema() {
    return this.schema;
  }

  public EventMetadata build() {
    return new EventMetadata(id, schema, action, timestamp);
  }
}
