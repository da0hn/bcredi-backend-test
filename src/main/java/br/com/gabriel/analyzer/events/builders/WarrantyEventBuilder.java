package br.com.gabriel.analyzer.events.builders;

import br.com.gabriel.analyzer.events.EventMetadata;
import br.com.gabriel.analyzer.events.WarrantyEvent;

public interface WarrantyEventBuilder {
  static WarrantyEventBuilder aWarrantyEvent() {
    return new WarrantyEventBuilderImpl();
  }
  void withEvent(EventMetadata event);
  void withProposalId(String proposalId);
  void withId(String id);
  void withValue(String value);
  void withWarrantyProvince(String warrantyProvince);
  WarrantyEvent build();
}
