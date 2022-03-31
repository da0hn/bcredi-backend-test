package br.com.gabriel.analyzer;

import br.com.gabriel.analyzer.events.Event;
import br.com.gabriel.analyzer.events.EventMetadataBuilder;
import br.com.gabriel.analyzer.events.ProponentEventBuilder;
import br.com.gabriel.analyzer.events.ProposalEventBuilder;
import br.com.gabriel.analyzer.events.WarrantyEventBuilder;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static br.com.gabriel.analyzer.events.EventAction.REMOVED;

public class EventSpliterator implements Spliterator<Event> {

  private static final long FIELD_NUMBER = 10;
  private final Spliterator<String> data;


  public EventSpliterator(Stream<String> data) {
    this.data = data.spliterator();
  }

  @Override public boolean tryAdvance(Consumer<? super Event> action) {

    var eventBuilder = EventMetadataBuilder.anEventMetadata();

    var isEventParsed = SpliteratorActionApplier.apply(
      this.data,
      eventBuilder::withId,
      eventBuilder::withSchema,
      eventBuilder::withAction,
      eventBuilder::withTimestamp
    );

    if(!isEventParsed) return false;

    return switch(eventBuilder.schema()) {
      case PROPONENT -> {
        var proponentBuilder = ProponentEventBuilder.aProponentEvent();

        proponentBuilder.withEvent(eventBuilder.build());

        var isProponentParsed = SpliteratorActionApplier.apply(
          this.data,
          proponentBuilder::withProposalId,
          proponentBuilder::withId,
          proponentBuilder::withName,
          proponentBuilder::withAge,
          proponentBuilder::withMonthlyIncome,
          proponentBuilder::withIsMain
        );

        if(isProponentParsed) {
          action.accept(proponentBuilder.build());
          yield true;
        }
        yield false;
      }
      case PROPOSAL -> {
        var proposalBuilder = ProposalEventBuilder.aProposalEvent();

        proposalBuilder.withEvent(eventBuilder.build());

        var isProposalParsed = SpliteratorActionApplier.apply(
          this.data,
          proposalBuilder::withId,
          proposalBuilder::withLoanValue,
          proposalBuilder::withNumberOfMonthlyInstallments
        );

        if(isProposalParsed) {
          action.accept(proposalBuilder.build());
          yield true;
        }

        yield false;
      }
      case WARRANTY -> {

        var warrantyBuilder = WarrantyEventBuilder.aWarrantyEvent();
        warrantyBuilder.withEvent(eventBuilder.build());

        var isWarrantyParsed = SpliteratorActionApplier.apply(
          this.data,
          warrantyBuilder::withProposalId,
          warrantyBuilder::withId
        );

        if(isWarrantyParsed && eventBuilder.action() != REMOVED) {
          isWarrantyParsed = SpliteratorActionApplier.apply(
            this.data,
            warrantyBuilder::withValue,
            warrantyBuilder::withWarrantyProvince
          );
        }

        if(isWarrantyParsed) {
          action.accept(warrantyBuilder.build());
          yield true;
        }

        yield false;
      }
    };
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
