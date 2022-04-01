package br.com.gabriel.analyzer.events.builders;

import br.com.gabriel.analyzer.events.EventMetadata;
import br.com.gabriel.analyzer.events.ProposalEvent;

public final class ProposalEventBuilderImpl implements ProposalEventBuilder {
  private EventMetadata event;
  private String id;
  private Double loanValue;
  private Long numberOfMonthlyInstallments;

  ProposalEventBuilderImpl() {
  }

  @Override public void withEvent(final EventMetadata event) {
    this.event = event;
  }

  @Override public void withId(final String id) {
    this.id = id;
  }

  @Override public void withLoanValue(final String loanValue) {
    this.loanValue = Double.valueOf(loanValue);
  }

  @Override public void withNumberOfMonthlyInstallments(final String numberOfMonthlyInstallments) {
    this.numberOfMonthlyInstallments = Long.valueOf(numberOfMonthlyInstallments);
  }

  @Override public ProposalEvent build() {
    return new ProposalEvent(this.event, this.id, this.loanValue, this.numberOfMonthlyInstallments);
  }
}
