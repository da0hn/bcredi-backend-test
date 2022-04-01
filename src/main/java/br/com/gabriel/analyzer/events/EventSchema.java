package br.com.gabriel.analyzer.events;

import br.com.gabriel.analyzer.events.builders.ProponentEventBuilder;
import br.com.gabriel.analyzer.events.builders.ProposalEventBuilder;
import br.com.gabriel.analyzer.events.builders.WarrantyEventBuilder;
import br.com.gabriel.analyzer.utils.SpliteratorActionApplier;

import java.util.Spliterator;
import java.util.function.Consumer;

import static br.com.gabriel.analyzer.events.EventAction.REMOVED;


public enum EventSchema {

  WARRANTY() {
    @Override public boolean convertFromSpliterator(
      final Spliterator<String> spliterator,
      final EventMetadata event,
      final Consumer<? super Event> action
    ) {
      final var warrantyBuilder = WarrantyEventBuilder.aWarrantyEvent();
      warrantyBuilder.withEvent(event);

      var isWarrantyParsed = SpliteratorActionApplier.apply(
        spliterator,
        warrantyBuilder::withProposalId,
        warrantyBuilder::withId
      );

      if(isWarrantyParsed && event.action() != REMOVED) {
        isWarrantyParsed = SpliteratorActionApplier.apply(
          spliterator,
          warrantyBuilder::withValue,
          warrantyBuilder::withWarrantyProvince
        );
      }

      if(!isWarrantyParsed) {
        return false;
      }
      action.accept(warrantyBuilder.build());
      return true;

    }
  },
  PROPONENT() {
    @Override public boolean convertFromSpliterator(
      final Spliterator<String> spliterator,
      final EventMetadata event,
      final Consumer<? super Event> action
    ) {
      final var proponentBuilder = ProponentEventBuilder.aProponentEvent();

      proponentBuilder.withEvent(event);

      final var isProponentParsed = SpliteratorActionApplier.apply(
        spliterator,
        proponentBuilder::withProposalId,
        proponentBuilder::withId,
        proponentBuilder::withName,
        proponentBuilder::withAge,
        proponentBuilder::withMonthlyIncome,
        proponentBuilder::withIsMain
      );

      if(!isProponentParsed) {
        return false;
      }
      action.accept(proponentBuilder.build());
      return true;
    }
  },
  PROPOSAL() {
    @Override public boolean convertFromSpliterator(
      final Spliterator<String> spliterator,
      final EventMetadata event,
      final Consumer<? super Event> action
    ) {
      final var proposalBuilder = ProposalEventBuilder.aProposalEvent();

      proposalBuilder.withEvent(event);

      final var isProposalParsed = SpliteratorActionApplier.apply(
        spliterator,
        proposalBuilder::withId,
        proposalBuilder::withLoanValue,
        proposalBuilder::withNumberOfMonthlyInstallments
      );

      if(!isProposalParsed) return false;

      action.accept(proposalBuilder.build());
      return true;
    }
  };


  public abstract boolean convertFromSpliterator(
    Spliterator<String> spliterator,
    EventMetadata event,
    Consumer<? super Event> action
  );

}
