package br.com.gabriel.analyzer;

import br.com.gabriel.analyzer.ProposalTestUtils.EventStubBuilder;
import br.com.gabriel.analyzer.domain.Proposal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("unit")
@DisplayName("Warranty province valid validation test")
class WarrantyProvinceValidationTest {


  @Test
  @DisplayName("Should return true when warranty province is valid")
  void test1() {

    final var events = EventStubBuilder.builder()
      .addValidProposalEvent()
      .addWarrantyEvent(3_413_113.45, "DF")
      .addWarrantyEvent(3_245_356.0, "DF")
      .addProponentEvent("Ismael Streich Jr.", 42, 62_615.64, true)
      .addProponentEvent("Mrs. Peter Wisozk", 41, 67_745.71, false)
      .build();

    final var proposal = new Proposal(ProposalTestUtils.PROPOSAL_ID, events);

    assertTrue(proposal.valid());
  }

  @Test
  @DisplayName("Should return false when at least one warranty province is invalid")
  void test2() {

    final var events = EventStubBuilder.builder()
      .addValidProposalEvent()
      .addWarrantyEvent(3_413_113.45, "DF")
      .addWarrantyEvent(3_245_356.0, "SC")
      .addProponentEvent("Ismael Streich Jr.", 42, 62_615.64, true)
      .addProponentEvent("Mrs. Peter Wisozk", 41, 67_745.71, false)
      .build();

    final var proposal = new Proposal(ProposalTestUtils.PROPOSAL_ID, events);

    assertFalse(proposal.valid());
  }

  @Test
  @DisplayName("Should return false when all warranty province is invalid")
  void test3() {

    final var events = EventStubBuilder.builder()
      .addValidProposalEvent()
      .addWarrantyEvent(3_413_113.45, "PR")
      .addWarrantyEvent(3_245_356.0, "SC")
      .addProponentEvent("Ismael Streich Jr.", 42, 62_615.64, true)
      .addProponentEvent("Mrs. Peter Wisozk", 41, 67_745.71, false)
      .build();

    final var proposal = new Proposal(ProposalTestUtils.PROPOSAL_ID, events);

    assertFalse(proposal.valid());
  }

}
