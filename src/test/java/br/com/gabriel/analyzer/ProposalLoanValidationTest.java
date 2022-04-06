package br.com.gabriel.analyzer;

import br.com.gabriel.analyzer.domain.Proposal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static br.com.gabriel.analyzer.ProposalTestUtils.EventStubBuilder;
import static br.com.gabriel.analyzer.ProposalTestUtils.PROPOSAL_ID;

@Tag("unit")
@DisplayName("Loan value validation test")
public class ProposalLoanValidationTest {

  @BeforeEach
  void setUp() {
  }

  @Test
  @DisplayName("Should return true when loan value between 30.000,00 and 3.000.000,00")
  void test1() {
    final var events = EventStubBuilder.builder()
      .addValidProposal()
      .addWarrantyEvent(3_413_113.45, "DF")
      .addWarrantyEvent(3_245_356.0, "DF")
      .addProponentEvent("Ismael Streich Jr.", 42, 62_615.64, true)
      .addProponentEvent("Mrs. Peter Wisozk", 41, 67_745.71, true)
      .build();

    final var proposal = new Proposal(PROPOSAL_ID, events);

    assertTrue(proposal.valid());
  }

  @Test
  @DisplayName("Should return false when loan value is lower than 30.000,00")
  void test2() {

    final var events = EventStubBuilder.builder()
      .addLowerLoanValueInvalidProposal()
      .addWarrantyEvent(3_413_113.45, "DF")
      .addWarrantyEvent(3_245_356.0, "DF")
      .addProponentEvent("Ismael Streich Jr.", 42, 62_615.64, true)
      .addProponentEvent("Mrs. Peter Wisozk", 41, 67_745.71, false)
      .build();

    final Proposal proposal = new Proposal(
      PROPOSAL_ID,
      events
    );

    assertFalse(proposal.valid());
  }

  @Test
  @DisplayName("Should return false when loan value is higher than 3.000.000,00")
  void test3() {

    final var events = EventStubBuilder.builder()
      .addHigherLoanValueInvalidProposal()
      .addWarrantyEvent(3_413_113.45, "DF")
      .addWarrantyEvent(3_245_356.0, "DF")
      .addProponentEvent("Ismael Streich Jr.", 42, 62_615.64, true)
      .addProponentEvent("Mrs. Peter Wisozk", 41, 67_745.71, false)
      .build();

    final Proposal proposal = new Proposal(
      PROPOSAL_ID,
      events
    );

    assertFalse(proposal.valid());
  }

}
