package br.com.gabriel.analyzer.events.executors;

import br.com.gabriel.analyzer.events.Event;
import br.com.gabriel.analyzer.events.EventAction;
import br.com.gabriel.analyzer.events.WarrantyEvent;

import java.util.List;

public class RemoveWarrantyEventExecutor implements ExternalEventExecutor {


  @Override public void execute(final List<Event> events) {

    final var warrantyRemovedEvents = events.stream()
      .filter(WarrantyEvent.class::isInstance)
      .map(WarrantyEvent.class::cast)
      .filter(warranty -> warranty.event().action() == EventAction.REMOVED)
      .map(WarrantyEvent::proposalId)
      .toList();

    events.removeIf(event -> warrantyRemovedEvents.contains(event.proposalId()));
  }

}
