package br.com.gabriel.analyzer.events;

public record ProposalEvent(
  Event event,
  String id,
  Double loanValue,
  Long numberOfMonthlyInstallments
) {
}
