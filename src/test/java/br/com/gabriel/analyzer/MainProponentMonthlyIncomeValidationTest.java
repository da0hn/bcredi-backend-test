package br.com.gabriel.analyzer;

import br.com.gabriel.analyzer.domain.Proposal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static br.com.gabriel.analyzer.ProposalTestUtils.EventStubBuilder;
import static br.com.gabriel.analyzer.ProposalTestUtils.PROPOSAL_ID;

@Tag("unit")
@DisplayName("Main proponent monthly income validation test")
class MainProponentMonthlyIncomeValidationTest {


  @Nested
  @DisplayName("Main proponent age is between 18 inclusive and 24 exclusive")
  class AgeBetween18And24Test {
    @Test
    @DisplayName("Should return true when loan installment value is greater than 4 times the main proponent monthly income")
    void test1() {
      final var events = EventStubBuilder.builder()
        .addProposalEvent(240_000.0, 24)
        .addWarrantyEvent(2_500_000.0, "DF")
        .addWarrantyEvent(2_500_000.0, "DF")
        .addProponentEvent("Ismael Streich Jr.", 22, 50_000.00, true)
        .addProponentEvent("Mrs. Peter Wisozk", 31, 10_000.00, false)
        .build();

      final var proposal = new Proposal(PROPOSAL_ID, events);

      assertTrue(proposal.valid());
    }


    @Test
    @DisplayName("Should return true when loan installment value is equal 4 times the main proponent monthly income")
    void test2() {
      final var events = EventStubBuilder.builder()
        .addProposalEvent(240_000.0, 24)
        .addWarrantyEvent(2_500_000.0, "DF")
        .addWarrantyEvent(2_500_000.0, "DF")
        .addProponentEvent("Ismael Streich Jr.", 22, 40_000.00, true)
        .addProponentEvent("Mrs. Peter Wisozk", 31, 10_000.00, false)
        .build();

      final var proposal = new Proposal(PROPOSAL_ID, events);

      assertTrue(proposal.valid());
    }

    @Test
    @DisplayName("Should return false when loan installment value is less than 4 times the main proponent monthly income")
    void test3() {
      final var events = EventStubBuilder.builder()
        .addProposalEvent(240_000.0, 24)
        .addWarrantyEvent(2_500_000.0, "DF")
        .addWarrantyEvent(2_500_000.0, "DF")
        .addProponentEvent("Ismael Streich Jr.", 22, 30_000.00, true)
        .addProponentEvent("Mrs. Peter Wisozk", 31, 10_000.00, false)
        .build();

      final var proposal = new Proposal(PROPOSAL_ID, events);

      assertFalse(proposal.valid());
    }
  }

  @Nested
  @DisplayName("Main proponent age is between 24 inclusive and 50 exclusive")
  class AgeBetween24And50Test {
    @Test
    @DisplayName("Should return true when loan installment value is greater than 3 times the main proponent monthly income")
    void test1() {
      final var events = EventStubBuilder.builder()
        .addProposalEvent(240_000.0, 24)
        .addWarrantyEvent(2_500_000.0, "DF")
        .addWarrantyEvent(2_500_000.0, "DF")
        .addProponentEvent("Ismael Streich Jr.", 25, 50_000.00, true)
        .addProponentEvent("Mrs. Peter Wisozk", 31, 10_000.00, false)
        .build();

      final var proposal = new Proposal(PROPOSAL_ID, events);

      assertTrue(proposal.valid());
    }


    @Test
    @DisplayName("Should return true when loan installment value is equal 3 times the main proponent monthly income")
    void test2() {
      final var events = EventStubBuilder.builder()
        .addProposalEvent(240_000.0, 24)
        .addWarrantyEvent(2_500_000.0, "DF")
        .addWarrantyEvent(2_500_000.0, "DF")
        .addProponentEvent("Ismael Streich Jr.", 25, 30_000.00, true)
        .addProponentEvent("Mrs. Peter Wisozk", 31, 10_000.00, false)
        .build();

      final var proposal = new Proposal(PROPOSAL_ID, events);

      assertTrue(proposal.valid());
    }

    @Test
    @DisplayName("Should return false when loan installment value is less than 3 times the main proponent monthly income")
    void test3() {
      final var events = EventStubBuilder.builder()
        .addProposalEvent(240_000.0, 24)
        .addWarrantyEvent(2_500_000.0, "DF")
        .addWarrantyEvent(2_500_000.0, "DF")
        .addProponentEvent("Ismael Streich Jr.", 25, 20_000.00, true)
        .addProponentEvent("Mrs. Peter Wisozk", 31, 10_000.00, false)
        .build();

      final var proposal = new Proposal(PROPOSAL_ID, events);

      assertFalse(proposal.valid());
    }
  }

  @Nested
  @DisplayName("Main proponent age greater than or equal 50")
  class AgeGreatherThan50Test {
    @Test
    @DisplayName("Should return true when loan installment value is greater than 2 times the main proponent monthly income")
    void test1() {
      final var events = EventStubBuilder.builder()
        .addProposalEvent(300_000.0, 30)
        .addWarrantyEvent(2_500_000.0, "DF")
        .addWarrantyEvent(2_500_000.0, "DF")
        .addProponentEvent("Ismael Streich Jr.", 51, 50_000.00, true)
        .addProponentEvent("Mrs. Peter Wisozk", 31, 10_000.00, false)
        .build();

      final var proposal = new Proposal(PROPOSAL_ID, events);

      assertTrue(proposal.valid());
    }


    @Test
    @DisplayName("Should return true when loan installment value is equal 2 times the main proponent monthly income")
    void test2() {
      final var events = EventStubBuilder.builder()
        .addProposalEvent(300_000.0, 30)
        .addWarrantyEvent(2_500_000.0, "DF")
        .addWarrantyEvent(2_500_000.0, "DF")
        .addProponentEvent("Ismael Streich Jr.", 51, 30_000.00, true)
        .addProponentEvent("Mrs. Peter Wisozk", 31, 10_000.00, false)
        .build();

      final var proposal = new Proposal(PROPOSAL_ID, events);

      assertTrue(proposal.valid());
    }

    @Test
    @DisplayName("Should return false when loan installment value is less than 2 times the main proponent monthly income")
    void test3() {
      final var events = EventStubBuilder.builder()
        .addProposalEvent(300_000.0, 30)
        .addWarrantyEvent(2_500_000.0, "DF")
        .addWarrantyEvent(2_500_000.0, "DF")
        .addProponentEvent("Ismael Streich Jr.", 51, 10_000.00, true)
        .addProponentEvent("Mrs. Peter Wisozk", 31, 10_000.00, false)
        .build();

      final var proposal = new Proposal(PROPOSAL_ID, events);

      assertFalse(proposal.valid());
    }
  }


}
