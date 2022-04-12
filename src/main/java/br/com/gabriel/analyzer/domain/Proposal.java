package br.com.gabriel.analyzer.domain;

import br.com.gabriel.analyzer.events.Event;
import br.com.gabriel.analyzer.events.ProponentEvent;
import br.com.gabriel.analyzer.events.ProposalEvent;
import br.com.gabriel.analyzer.events.WarrantyEvent;
import br.com.gabriel.analyzer.validators.Validable;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static br.com.gabriel.analyzer.domain.ProposalConstants.MAX_LOAN_VALUE;
import static br.com.gabriel.analyzer.domain.ProposalConstants.MAX_YEAR_INSTALLMENTS;
import static br.com.gabriel.analyzer.domain.ProposalConstants.MIN_AGE;
import static br.com.gabriel.analyzer.domain.ProposalConstants.MIN_LOAN_VALUE;
import static br.com.gabriel.analyzer.domain.ProposalConstants.MIN_YEAR_INSTALLMENTS;
import static br.com.gabriel.analyzer.events.EventAction.ADDED;

public class Proposal implements Validable {


  private static final Logger LOGGER = Logger.getLogger("proposal");
  private final String proposalId;
  private final List<Event> events;

  public Proposal(final String proposalId, final List<Event> events) {
    this.proposalId = proposalId;
    this.events = Collections.unmodifiableList(events);
  }

  public static Proposal fromEntry(final Map.Entry<String, ? extends List<Event>> entry) {
    return new Proposal(entry.getKey(), entry.getValue());
  }

  public boolean valid() {
    try {
      ifLoanValueOutOfRangeThrowException();
      ifMonthlyInstallmentsOutOfRangeThrowException();
      ifNumberOfProponentsIsLowerThanTwoThrowException();
      ifNotHaveExactlyOneMainProponentThrowException();
      ifProponentAgeLessThanEighteenThrowException();
      ifNumberOfWarrantyIsLowerThanOneThrowException();
      ifWarrantyValueIsLowerThanTwiceLoanValueThrowException();
      ifWarrantyProvinceIsNotValidThrowException();
    }
    catch(final ProposalInvalidException exception) {
      LOGGER.info(exception.getMessage());
      return false;
    }
    return true;
  }

  private void ifWarrantyProvinceIsNotValidThrowException() {
    final var maybeInvalidProvince = this.events.stream()
      .filter(WarrantyEvent.class::isInstance)
      .map(WarrantyEvent.class::cast)
      .map(WarrantyEvent::warrantyProvince)
      .filter(province -> List.of("SC", "PR", "RS").contains(province))
      .findAny();

    if(maybeInvalidProvince.isPresent()) {
      throw new ProposalInvalidException("proposal.invalid-warranty-province");
    }

  }

  private void ifWarrantyValueIsLowerThanTwiceLoanValueThrowException() {
    final var totalWarrantyValue = this.events.stream()
      .filter(WarrantyEvent.class::isInstance)
      .map(WarrantyEvent.class::cast)
      .map(WarrantyEvent::value)
      .reduce(Double::sum)
      .orElse(0.0);

    final var proposalEvent = this.proposalEvent();

    if(totalWarrantyValue < proposalEvent.loanValue() * 2) {
      throw new ProposalInvalidException("proposal.invalid-warranty-value");
    }
  }

  private void ifNumberOfWarrantyIsLowerThanOneThrowException() {
    final var numberOfWarranty = this.events.stream()
      .filter(WarrantyEvent.class::isInstance)
      .map(WarrantyEvent.class::cast)
      .count();
    if(numberOfWarranty < 1) {
      throw new ProposalInvalidException("proposal.invalid-warranty-number");
    }
  }

  private void ifProponentAgeLessThanEighteenThrowException() {
    final var proponentsWithInvalidAge = this.events.stream()
      .filter(ProponentEvent.class::isInstance)
      .map(ProponentEvent.class::cast)
      .filter(proponent -> proponent.age() < MIN_AGE)
      .count();

    if(proponentsWithInvalidAge > 0) {
      throw new ProposalInvalidException("proposal.invalid-proponent-age");
    }
  }

  private void ifNotHaveExactlyOneMainProponentThrowException() {

    final var numberOfMainProponent = this.events.stream()
      .filter(ProponentEvent.class::isInstance)
      .map(ProponentEvent.class::cast)
      .filter(ProponentEvent::isMain)
      .count();

    if(numberOfMainProponent != 1) {
      throw new ProposalInvalidException("proposal.invalid-main-proponent");
    }
  }

  private void ifNumberOfProponentsIsLowerThanTwoThrowException() {
    final var numberOfProponents = this.events.stream()
      .filter(ProponentEvent.class::isInstance)
      .count();

    if(numberOfProponents < 2) {
      throw new ProposalInvalidException("proposal.invalid-proponent-number");
    }
  }

  private void ifMonthlyInstallmentsOutOfRangeThrowException() {
    final var proposalEvent = proposalEvent();
    if(proposalEvent.numberOfMonthlyInstallments() < MIN_YEAR_INSTALLMENTS || proposalEvent.numberOfMonthlyInstallments() > MAX_YEAR_INSTALLMENTS) {
      throw new ProposalInvalidException("proposal.years-installments.out-of-range");
    }
  }

  private void ifLoanValueOutOfRangeThrowException() {
    final var proposalEvent = proposalEvent();
    if(proposalEvent.loanValue() < MIN_LOAN_VALUE || proposalEvent.loanValue() > MAX_LOAN_VALUE) {
      throw new ProposalInvalidException("proposal.loan_value.out-of-range");
    }
  }

  private ProposalEvent proposalEvent() {
    return this.events.stream()
      .filter(ProposalEvent.class::isInstance)
      .map(ProposalEvent.class::cast)
      .filter(proposalEvent -> proposalEvent.proposalId().equals(this.proposalId))
      .filter(proposalEvent -> proposalEvent.event().action() == ADDED)
      .findFirst()
      .orElseThrow(() -> new ProposalInvalidException("proposal.proposal-event.not-found"));
  }
}
