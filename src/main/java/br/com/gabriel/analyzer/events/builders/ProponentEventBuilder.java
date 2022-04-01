package br.com.gabriel.analyzer.events.builders;

import br.com.gabriel.analyzer.events.EventMetadata;
import br.com.gabriel.analyzer.events.ProponentEvent;

public interface ProponentEventBuilder {

  static ProponentEventBuilder aProponentEvent() {
    return new ProponentEventBuilderImpl();
  }
  void withEvent(EventMetadata event);
  void withProposalId(String proposalId);
  void withId(String id);
  void withName(String name);
  void withAge(String age);
  void withMonthlyIncome(String monthlyIncome);
  void withIsMain(String isMain);
  ProponentEvent build();
}
