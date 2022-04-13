package br.com.gabriel.analyzer;

import br.com.gabriel.analyzer.ProposalTestUtils.EventStubBuilder;
import br.com.gabriel.analyzer.domain.Proposal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("unit")
@DisplayName("Warranty value validation test")
class WarrantyValueValidationTest {

  @Test
  @DisplayName("Should return true when the sum of warranty values is equal than twice of loan value")
  void test1() {
    final var events = EventStubBuilder.builder()
      .addProposalEvent(200_000.0, 24)
      .addWarrantyEvent(200_000.0, "DF")
      .addWarrantyEvent(200_000.0, "DF")
      .addProponentEvent("Ismael Streich Jr.", 42, 62_615.64, true)
      .addProponentEvent("Mrs. Peter Wisozk", 41, 67_745.71, false)
      .build();

    final var proposal = new Proposal(ProposalTestUtils.PROPOSAL_ID, events);

    assertTrue(proposal.valid());
  }

  @Test
  @DisplayName("Should return true when the sum of warranty values is greater than twice of loan value")
  void test2() {
    final var events = EventStubBuilder.builder()
      .addProposalEvent(200_000.0, 24)
      .addWarrantyEvent(250_000.0, "DF")
      .addWarrantyEvent(250_000.0, "DF")
      .addProponentEvent("Ismael Streich Jr.", 42, 62_615.64, true)
      .addProponentEvent("Mrs. Peter Wisozk", 41, 67_745.71, false)
      .build();

    final var proposal = new Proposal(ProposalTestUtils.PROPOSAL_ID, events);

    assertTrue(proposal.valid());
  }

  @Test
  @DisplayName("Should return false when the sum of warranty values is lower than twice of loan value")
  void test3() {
    final var events = EventStubBuilder.builder()
      .addProposalEvent(300_000.0, 24)
      .addWarrantyEvent(200_000.0, "DF")
      .addWarrantyEvent(200_000.0, "DF")
      .addProponentEvent("Ismael Streich Jr.", 42, 62_615.64, true)
      .addProponentEvent("Mrs. Peter Wisozk", 41, 67_745.71, false)
      .build();

    final var proposal = new Proposal(ProposalTestUtils.PROPOSAL_ID, events);

    assertFalse(proposal.valid());
  }
}
