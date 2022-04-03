package br.com.gabriel.analyzer.events;

public record ProposalEvent(
  EventMetadata event,
  String proposalId,
  Double loanValue,
  Integer numberOfMonthlyInstallments
) implements Event {
}
