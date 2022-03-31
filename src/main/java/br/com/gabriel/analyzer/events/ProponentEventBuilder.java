package br.com.gabriel.analyzer.events;

public final class ProponentEventBuilder {
  private EventMetadata event;
  private String proposalId;
  private String id;
  private String name;
  private Integer age;
  private Double monthlyIncome;
  private Boolean isMain;

  private ProponentEventBuilder() {
  }

  public static ProponentEventBuilder aProponentEvent() {
    return new ProponentEventBuilder();
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

  public void withName(String name) {
    this.name = name;
  }

  public void withAge(String age) {
    this.age = Integer.parseInt(age);
  }

  public void withMonthlyIncome(String monthlyIncome) {
    this.monthlyIncome = Double.parseDouble(monthlyIncome);
  }

  public void withIsMain(String isMain) {
    this.isMain = Boolean.parseBoolean(isMain);
  }

  public ProponentEvent build() {
    return new ProponentEvent(event, proposalId, id, name, age, monthlyIncome, isMain);
  }
}
