package br.com.gabriel.analyzer;

import br.com.gabriel.analyzer.events.Event;
import br.com.gabriel.analyzer.events.EventSchema;
import br.com.gabriel.analyzer.events.builders.EventMetadataBuilder;
import br.com.gabriel.analyzer.utils.SpliteratorActionApplier;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.BaseStream;
import java.util.stream.Stream;

public class EventSpliterator implements Spliterator<Event> {

  private final Spliterator<String> data;


  EventSpliterator(final BaseStream<String, Stream<String>> data) {
    this.data = data.spliterator();
  }

  @Override public boolean tryAdvance(final Consumer<? super Event> action) {

    final var eventBuilder = EventMetadataBuilder.anEventMetadata();

    final var isEventParsed = SpliteratorActionApplier.apply(
      this.data,
      eventBuilder::withId,
      eventBuilder::withSchema,
      eventBuilder::withAction,
      eventBuilder::withTimestamp
    );

    if(!isEventParsed) return false;

    final EventSchema schema = eventBuilder.schema();

    return schema.convertFromSpliterator(this.data, eventBuilder.build(), action);
  }

  @Override public Spliterator<Event> trySplit() {
    return null;
  }

  @Override public long estimateSize() {
    return this.data.estimateSize();
  }

  @Override public int characteristics() {
    return this.data.characteristics();
  }

}
