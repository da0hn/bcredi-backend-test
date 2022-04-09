package br.com.gabriel.analyzer.domain;

import br.com.gabriel.analyzer.events.Event;
import br.com.gabriel.analyzer.events.ProponentEvent;
import br.com.gabriel.analyzer.events.ProposalEvent;
import br.com.gabriel.analyzer.validators.Validable;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static br.com.gabriel.analyzer.domain.ProposalConstants.MAX_LOAN_VALUE;
import static br.com.gabriel.analyzer.domain.ProposalConstants.MAX_YEAR_INSTALLMENTS;
import static br.com.gabriel.analyzer.domain.ProposalConstants.MIN_LOAN_VALUE;
import static br.com.gabriel.analyzer.domain.ProposalConstants.MIN_YEAR_INSTALLMENTS;
import static br.com.gabriel.analyzer.events.EventAction.ADDED;

public class Proposal implements Validable {


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
      ifInvalidInstallmentsThrowException();
      ifInvalidProponentNumberThrowException();
    }
    catch(final ProposalInvalidException exception) {
      System.err.println(exception.getMessage());
      return false;
    }
    return true;
  }

  private void ifInvalidProponentNumberThrowException() {
    final var numberOfProponents = this.events.stream()
      .filter(ProponentEvent.class::isInstance)
      .count();

    if(numberOfProponents < 2) {
      throw new ProposalInvalidException("proposal.invalid_proponent_number");
    }
  }

  private void ifInvalidInstallmentsThrowException() {
    final var proposalEvent = proposalEvent();
    if(proposalEvent.numberOfMonthlyInstallments() < MIN_YEAR_INSTALLMENTS || proposalEvent.numberOfMonthlyInstallments() > MAX_YEAR_INSTALLMENTS) {
      throw new ProposalInvalidException("proposal.years_installments.out_of_bound");
    }
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
