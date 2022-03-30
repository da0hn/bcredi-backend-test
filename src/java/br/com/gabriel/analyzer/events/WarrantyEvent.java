package br.com.gabriel.analyzer.events;

public record WarrantyEvent(
  Event event,
  String proposalId,
  String id,
  Double value,
  String warrantyProvince
) {
}
