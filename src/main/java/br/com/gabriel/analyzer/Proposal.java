package br.com.gabriel.analyzer;

import br.com.gabriel.analyzer.events.Event;

import java.util.List;

public class Proposal {

  private static final int ONE_MONTH = 1;
  private static final int ONE_YEAR = 12 * ONE_MONTH;
  private static final int MIN_YEAR_INSTALLMENTS = ONE_YEAR * 2;
  private static final int MAX_YEAR_INSTALLMENTS = ONE_YEAR * 15;
  private final String proposalId;
  private final List<Event> events;

  public Proposal(final String proposalId, final List<Event> events) {
    this.proposalId = proposalId;
    this.events = events;
  }
}
