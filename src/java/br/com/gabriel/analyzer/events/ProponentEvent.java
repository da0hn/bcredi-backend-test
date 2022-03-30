package br.com.gabriel.analyzer.events;

public record ProponentEvent(
  Event event,
  String proposalId,
  String id,
  String name,
  Integer age,
  Double monthlyIncome,
  Boolean isMain
) {
}
