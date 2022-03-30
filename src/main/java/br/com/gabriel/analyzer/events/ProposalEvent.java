package br.com.gabriel.analyzer.events;

public record ProposalEvent(
  EventMetadata event,
  String id,
  Double loanValue,
  Long numberOfMonthlyInstallments
) implements Event {
}
