package br.com.gabriel.analyzer.events;

public final class ProposalEventBuilder {
  private EventMetadata event;
  private String id;
  private Double loanValue;
  private Long numberOfMonthlyInstallments;

  private ProposalEventBuilder() {
  }

  public static ProposalEventBuilder aProposalEvent() {
    return new ProposalEventBuilder();
  }

  public void withEvent(EventMetadata event) {
    this.event = event;
  }

  public void withId(String id) {
    this.id = id;
  }

  public void withLoanValue(String loanValue) {
    this.loanValue = Double.valueOf(loanValue);
  }

  public void withNumberOfMonthlyInstallments(String numberOfMonthlyInstallments) {
    this.numberOfMonthlyInstallments = Long.valueOf(numberOfMonthlyInstallments);
  }

  public ProposalEvent build() {
    return new ProposalEvent(event, id, loanValue, numberOfMonthlyInstallments);
  }
}
