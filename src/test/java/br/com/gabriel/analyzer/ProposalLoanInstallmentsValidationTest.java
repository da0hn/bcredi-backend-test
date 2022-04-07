package br.com.gabriel.analyzer;

import br.com.gabriel.analyzer.domain.Proposal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static br.com.gabriel.analyzer.domain.ProposalConstants.MAX_YEAR_INSTALLMENTS;
import static br.com.gabriel.analyzer.domain.ProposalConstants.MIN_YEAR_INSTALLMENTS;

@Tag("unit")
@DisplayName("Proposal loan installments validation test")
class ProposalLoanInstallmentsValidationTest {

  private static final int INVALID_LOWER_YEARS_INSTALLMENTS = MIN_YEAR_INSTALLMENTS - 1;
  private static final int INVALID_GREATER_YEARS_INSTALLMENTS = MAX_YEAR_INSTALLMENTS + 1;
  private static final int VALID_YEARS_INSTALLMENTS = MIN_YEAR_INSTALLMENTS + 2;

  @Test
  @DisplayName("Should return true when loan payment installments greater than 2 years and lower than 5 years")
  void test1() {

    final var events = ProposalTestUtils.EventStubBuilder.builder()
      .addProposalEvent(45_000.00, VALID_YEARS_INSTALLMENTS)
      .addWarrantyEvent(3_413_113.45, "DF")
      .addWarrantyEvent(3_245_356.0, "DF")
      .addProponentEvent("Ismael Streich Jr.", 42, 62_615.64, true)
      .addProponentEvent("Mrs. Peter Wisozk", 41, 67_745.71, false)
      .build();

    final var proposal = new Proposal(ProposalTestUtils.PROPOSAL_ID, events);

    assertTrue(proposal.valid());
  }


  @Test
  @DisplayName("Should return false when loan payment installments lower than 2 years")
  void test2() {

    final var events = ProposalTestUtils.EventStubBuilder.builder()
      .addProposalEvent(45_000.00, INVALID_LOWER_YEARS_INSTALLMENTS)
      .addWarrantyEvent(3_413_113.45, "DF")
      .addWarrantyEvent(3_245_356.0, "DF")
      .addProponentEvent("Ismael Streich Jr.", 42, 62_615.64, true)
      .addProponentEvent("Mrs. Peter Wisozk", 41, 67_745.71, false)
      .build();

    final var proposal = new Proposal(ProposalTestUtils.PROPOSAL_ID, events);

    assertFalse(proposal.valid());
  }


  @Test
  @DisplayName("Should return false when loan payment installments greater than 5 years")
  void test3() {

    final var events = ProposalTestUtils.EventStubBuilder.builder()
      .addProposalEvent(45_000.00, INVALID_GREATER_YEARS_INSTALLMENTS)
      .addWarrantyEvent(3_413_113.45, "DF")
      .addWarrantyEvent(3_245_356.0, "DF")
      .addProponentEvent("Ismael Streich Jr.", 42, 62_615.64, true)
      .addProponentEvent("Mrs. Peter Wisozk", 41, 67_745.71, false)
      .build();

    final var proposal = new Proposal(ProposalTestUtils.PROPOSAL_ID, events);

    assertFalse(proposal.valid());
  }

}
