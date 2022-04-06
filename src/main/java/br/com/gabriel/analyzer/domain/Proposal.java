package br.com.gabriel.analyzer.domain;

import br.com.gabriel.analyzer.events.Event;
import br.com.gabriel.analyzer.events.ProposalEvent;
import br.com.gabriel.analyzer.validators.Validable;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static br.com.gabriel.analyzer.events.EventAction.ADDED;

public class Proposal implements Validable {

  private static final int ONE_MONTH = 1;
  private static final int ONE_YEAR = 12 * ONE_MONTH;
  private static final int MIN_YEAR_INSTALLMENTS = ONE_YEAR * 2;
  private static final int MAX_YEAR_INSTALLMENTS = ONE_YEAR * 15;
  private static final double MIN_LOAN_VALUE = 30_000;
  private static final double MAX_LOAN_VALUE = 3_000_000;
  private final String proposalId;
  private final List<Event> events;

  public Proposal(final String proposalId, final List<Event> events) {
    this.proposalId = proposalId;
    this.events = Collections.unmodifiableList(events);
  }

  public static Proposal fromEntry(final Map.Entry<String, List<Event>> entry) {
    return new Proposal(entry.getKey(), entry.getValue());
  }

  public boolean valid() {
    try {
      ifInvalidLoanvalueThrowException();
    }
    catch(final ProposalInvalidException exception) {
      System.err.println(exception.getMessage());
      return false;
    }
    return true;
  }

  private void ifInvalidLoanvalueThrowException() {
    final var proposalEvent = proposalEvent();
    if(proposalEvent.loanValue() < MIN_LOAN_VALUE || proposalEvent.loanValue() > MAX_LOAN_VALUE) {
      throw new ProposalInvalidException("proposal.loan_value.out_of_bound");
    }
  }

  private ProposalEvent proposalEvent() {
    return this.events.stream()
      .filter(ProposalEvent.class::isInstance)
      .map(ProposalEvent.class::cast)
      .filter(proposalEvent -> proposalEvent.proposalId().equals(this.proposalId))
      .filter(proposalEvent -> proposalEvent.event().action() == ADDED)
      .findFirst()
      .orElseThrow(() -> new ProposalInvalidException("proposal.proposal_event.not_found"));
  }
}
