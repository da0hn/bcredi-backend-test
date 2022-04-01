package br.com.gabriel.analyzer.events.builders;

import br.com.gabriel.analyzer.events.EventAction;
import br.com.gabriel.analyzer.events.EventMetadata;
import br.com.gabriel.analyzer.events.EventSchema;

import java.time.format.DateTimeFormatter;

public interface EventMetadataBuilder {

  String FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
  static EventMetadataBuilder anEventMetadata() {
    return new EventMetadataBuilderImpl();
  }
  void withId(String id);
  void withSchema(String schema);
  void withAction(String action);
  void withTimestamp(CharSequence timestamp);
  EventSchema schema();
  EventMetadata build();
  EventAction action();
}
