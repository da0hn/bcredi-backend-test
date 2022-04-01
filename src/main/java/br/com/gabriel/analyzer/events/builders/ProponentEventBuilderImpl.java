package br.com.gabriel.analyzer.events.builders;

import br.com.gabriel.analyzer.events.EventMetadata;
import br.com.gabriel.analyzer.events.ProponentEvent;

public final class ProponentEventBuilderImpl implements ProponentEventBuilder {
  private EventMetadata event;
  private String proposalId;
  private String id;
  private String name;
  private Integer age;
  private Double monthlyIncome;
  private Boolean isMain;

  ProponentEventBuilderImpl() {
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

  @Override public void withName(final String name) {
    this.name = name;
  }

  @Override public void withAge(final String age) {
    this.age = Integer.parseInt(age);
  }

  @Override public void withMonthlyIncome(final String monthlyIncome) {
    this.monthlyIncome = Double.parseDouble(monthlyIncome);
  }

  @Override public void withIsMain(final String isMain) {
    this.isMain = Boolean.parseBoolean(isMain);
  }

  @Override public ProponentEvent build() {
    return new ProponentEvent(this.event, this.proposalId, this.id, this.name, this.age, this.monthlyIncome, this.isMain);
  }
}
