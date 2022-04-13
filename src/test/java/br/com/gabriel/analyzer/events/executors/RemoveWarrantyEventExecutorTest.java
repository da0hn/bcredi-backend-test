package br.com.gabriel.analyzer.events.executors;

import br.com.gabriel.analyzer.events.WarrantyEvent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static br.com.gabriel.analyzer.ProposalTestUtils.EventStubBuilder;
import static br.com.gabriel.analyzer.ProposalTestUtils.anEvent;
import static br.com.gabriel.analyzer.events.EventAction.ADDED;
import static br.com.gabriel.analyzer.events.EventAction.REMOVED;
import static br.com.gabriel.analyzer.events.EventSchema.WARRANTY;

@Tag("unit")
@DisplayName("Remove warranty event executor test")
class RemoveWarrantyEventExecutorTest {

  private RemoveWarrantyEventExecutor removeWarrantyEventExecutor;

  @BeforeEach
  void setUp() {
    this.removeWarrantyEventExecutor = new RemoveWarrantyEventExecutor();
  }

  @Test
  @DisplayName("Should remove warranty when find REMOVED event action")
  void test1() {

    final var warrantyRemovedId = UUID.randomUUID().toString();

    final var events = new ArrayList<>(
      EventStubBuilder.builder()
        .addValidProposalEvent()
        .addWarrantyEvent(anEvent(WARRANTY, ADDED), warrantyRemovedId, 20_000_000.00, "DF")
        .addWarrantyEvent(20_000_000.00, "MT")
        .addWarrantyEvent(anEvent(WARRANTY, REMOVED), warrantyRemovedId, 20_000_000.00, "DF")
        .addProponentEvent("Ismael Streich Jr.", 42, 62_615.64, true)
        .addProponentEvent("Mrs. Peter Wisozk", 41, 67_745.71, false)
        .build()
    );

    this.removeWarrantyEventExecutor.execute(events);

    Assertions.assertThat(events)
      .filteredOn(WarrantyEvent.class::isInstance)
      .map(WarrantyEvent.class::cast)
      .map(WarrantyEvent::id)
      .doesNotContain(warrantyRemovedId);
  }

  @Test
  @DisplayName("Should not remove warranty when not find REMOVED event action")
  void test2() {

    final var warrantyRemovedId = UUID.randomUUID().toString();

    final var events = new ArrayList<>(
      EventStubBuilder.builder()
        .addWarrantyEvent(anEvent(WARRANTY, ADDED), warrantyRemovedId, 20_000_000.00, "DF")
        .addWarrantyEvent(20_000_000.00, "MT")
        .build()
    );

    this.removeWarrantyEventExecutor.execute(events);

    Assertions.assertThat(events)
      .filteredOn(WarrantyEvent.class::isInstance)
      .map(WarrantyEvent.class::cast)
      .map(WarrantyEvent::id)
      .contains(warrantyRemovedId);
  }

  @Test
  @DisplayName("Should only remove warranty with REMOVED event action when not found the warranty ADDED event action")
  void test3() {

    final var warrantyRemovedId = UUID.randomUUID().toString();

    final var events = new ArrayList<>(
      EventStubBuilder.builder()
        .addValidProposalEvent()
        .addWarrantyEvent(anEvent(WARRANTY, REMOVED), warrantyRemovedId, 20_000_000.00, "DF")
        .addWarrantyEvent(20_000_000.00, "MT")
        .addProponentEvent("Ismael Streich Jr.", 42, 62_615.64, true)
        .addProponentEvent("Mrs. Peter Wisozk", 41, 67_745.71, false)
        .build()
    );

    this.removeWarrantyEventExecutor.execute(events);


    Assertions.assertThat(events)
      .filteredOn(WarrantyEvent.class::isInstance)
      .map(WarrantyEvent.class::cast)
      .map(WarrantyEvent::id)
      .doesNotContain(warrantyRemovedId);
  }
}
