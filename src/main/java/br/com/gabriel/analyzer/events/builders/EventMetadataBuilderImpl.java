package br.com.gabriel.analyzer.events.builders;

import br.com.gabriel.analyzer.events.EventAction;
import br.com.gabriel.analyzer.events.EventMetadata;
import br.com.gabriel.analyzer.events.EventSchema;

import java.time.LocalDateTime;
import java.util.Locale;

public final class EventMetadataBuilderImpl implements EventMetadataBuilder {
  private String id;
  private EventSchema schema;
  private EventAction action;
  private LocalDateTime timestamp;

  EventMetadataBuilderImpl() {
  }

  @Override public void withId(final String id) {
    this.id = id;
  }

  @Override public void withSchema(final String schema) {
    this.schema = EventSchema.valueOf(schema.toUpperCase(Locale.ROOT));
  }

  @Override public void withAction(final String action) {
    this.action = EventAction.valueOf(action.toUpperCase(Locale.ROOT));
  }

  @Override public void withTimestamp(final CharSequence timestamp) {
    this.timestamp = LocalDateTime.parse(timestamp, EventMetadataBuilder.formatter);
  }

  @Override public EventSchema schema() {
    return this.schema;
  }

  @Override public EventMetadata build() {
    return new EventMetadata(this.id, this.schema, this.action, this.timestamp);
  }

  @Override public EventAction action() {
    return this.action;
  }
}
