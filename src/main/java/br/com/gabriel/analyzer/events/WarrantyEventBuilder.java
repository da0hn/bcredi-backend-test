package br.com.gabriel.analyzer.events;

public final class WarrantyEventBuilder {
  private EventMetadata event;
  private String proposalId;
  private String id;
  private Double value;
  private String warrantyProvince;

  private WarrantyEventBuilder() {
  }

  public static WarrantyEventBuilder aWarrantyEvent() {
    return new WarrantyEventBuilder();
  }

  public void withEvent(EventMetadata event) {
    this.event = event;
  }

  public void withProposalId(String proposalId) {
    this.proposalId = proposalId;
  }

  public void withId(String id) {
    this.id = id;
  }

  public void withValue(String value) {
    this.value = Double.valueOf(value);
  }

  public void withWarrantyProvince(String warrantyProvince) {
    this.warrantyProvince = warrantyProvince;
  }

  public WarrantyEvent build() {
    return new WarrantyEvent(event, proposalId, id, value, warrantyProvince);
  }
}
