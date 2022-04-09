package br.com.gabriel.analyzer;

import br.com.gabriel.analyzer.domain.Proposal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("unit")
@DisplayName("Number of proponent by proposal validation test")
class ProposalProponentNumberValidationTest {


  @Test
  @DisplayName("Should return true when have 2 or more proponents in proposal")
  void test1() {

    final var events = ProposalTestUtils.EventStubBuilder.builder()
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
  @DisplayName("Should return false when have 1 proponent in proposal")
  void test2() {
    final var events = ProposalTestUtils.EventStubBuilder.builder()
      .addValidProposalEvent()
      .addWarrantyEvent(3_413_113.45, "DF")
      .addWarrantyEvent(3_245_356.0, "DF")
      .addProponentEvent("Mrs. Peter Wisozk", 41, 67_745.71, false)
      .build();

    final var proposal = new Proposal(ProposalTestUtils.PROPOSAL_ID, events);

    assertFalse(proposal.valid());
  }

  @Test
  @DisplayName("Should return false when not have proponent in proposal")
  void test3() {
    final var events = ProposalTestUtils.EventStubBuilder.builder()
      .addValidProposalEvent()
      .addWarrantyEvent(3_413_113.45, "DF")
      .addWarrantyEvent(3_245_356.0, "DF")
      .addProponentEvent("Mrs. Peter Wisozk", 41, 67_745.71, false)
      .build();

    final var proposal = new Proposal(ProposalTestUtils.PROPOSAL_ID, events);

    assertFalse(proposal.valid());
  }


}
