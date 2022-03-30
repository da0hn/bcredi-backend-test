package br.com.gabriel.analyzer.events;

public record WarrantyEvent(
  EventMetadata event,
  String proposalId,
  String id,
  Double value,
  String warrantyProvince
) implements Event {
}
