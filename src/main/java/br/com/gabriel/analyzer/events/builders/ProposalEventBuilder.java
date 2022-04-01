package br.com.gabriel.analyzer.events.builders;

import br.com.gabriel.analyzer.events.EventMetadata;
import br.com.gabriel.analyzer.events.ProposalEvent;

public interface ProposalEventBuilder {

  static ProposalEventBuilder aProposalEvent() {
    return new ProposalEventBuilderImpl();
  }
  void withEvent(EventMetadata event);
  void withId(String id);
  void withLoanValue(String loanValue);
  void withNumberOfMonthlyInstallments(String numberOfMonthlyInstallments);
  ProposalEvent build();
}
