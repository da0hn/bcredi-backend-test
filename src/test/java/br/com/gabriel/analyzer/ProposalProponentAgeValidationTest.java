package br.com.gabriel.analyzer;


import br.com.gabriel.analyzer.domain.Proposal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("unit")
@DisplayName("Proponent age validation test")
class ProposalProponentAgeValidationTest {


  @Test
  @DisplayName("Should return true when proponents have age greater than 18 years")
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
  @DisplayName("Should return true when proponents have age equal 18 years")
  void test2() {
    final var events = ProposalTestUtils.EventStubBuilder.builder()
      .addValidProposalEvent()
      .addWarrantyEvent(3_413_113.45, "DF")
      .addWarrantyEvent(3_245_356.0, "DF")
      .addProponentEvent("Ismael Streich Jr.", 18, 62_615.64, true)
      .addProponentEvent("Mrs. Peter Wisozk", 18, 67_745.71, false)
      .build();

    final var proposal = new Proposal(ProposalTestUtils.PROPOSAL_ID, events);

    assertTrue(proposal.valid());
  }

  @Test
  @DisplayName("Should return false when proponents have age less than 18 years")
  void test3() {
    final var events = ProposalTestUtils.EventStubBuilder.builder()
      .addValidProposalEvent()
      .addWarrantyEvent(3_413_113.45, "DF")
      .addWarrantyEvent(3_245_356.0, "DF")
      .addProponentEvent("Ismael Streich Jr.", 17, 62_615.64, true)
      .addProponentEvent("Mrs. Peter Wisozk", 16, 67_745.71, false)
      .build();

    final var proposal = new Proposal(ProposalTestUtils.PROPOSAL_ID, events);

    assertFalse(proposal.valid());
  }
}
