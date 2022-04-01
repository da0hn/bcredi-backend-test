package br.com.gabriel.analyzer.events.builders;

import br.com.gabriel.analyzer.events.EventMetadata;
import br.com.gabriel.analyzer.events.WarrantyEvent;

public final class WarrantyEventBuilderImpl implements WarrantyEventBuilder {
  private EventMetadata event;
  private String proposalId;
  private String id;
  private Double value;
  private String warrantyProvince;

  WarrantyEventBuilderImpl() {
  }


  @Override public void withEvent(final EventMetadata event) {
    this.event = event;
  }

  @Override public void withProposalId(final String proposalId) {
    this.proposalId = proposalId;
  }

  @Override public void withId(final String id) {
    this.id = id;
  }

  @Override public void withValue(final String value) {
    this.value = Double.valueOf(value);
  }

  @Override public void withWarrantyProvince(final String warrantyProvince) {
    this.warrantyProvince = warrantyProvince;
  }

  @Override public WarrantyEvent build() {
    return new WarrantyEvent(this.event, this.proposalId, this.id, this.value, this.warrantyProvince);
  }
}
