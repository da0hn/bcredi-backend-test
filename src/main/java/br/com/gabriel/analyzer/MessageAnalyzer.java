package br.com.gabriel.analyzer;

import br.com.gabriel.analyzer.domain.Proposal;
import br.com.gabriel.analyzer.events.Event;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MessageAnalyzer implements Analyzer {


  @Override public String execute(final Collection<String> messages) {

    final var messageStream = messages.stream()
      .flatMap(message -> Arrays.stream(message.split(",")));

    final var events = StreamSupport.stream(
      new EventSpliterator(messageStream),
      false
    ).toList();

    final Map<String, List<Event>> eventsByProposalId = events.stream()
      .collect(Collectors.groupingBy(Event::proposalId));

    final List<Proposal> proposals = eventsByProposalId.entrySet()
      .stream()
      .map(Proposal::fromEntry)
      .toList();

    return proposals.stream()
      .filter(Proposal::valid)
      .map(Proposal::proposalId)
      .collect(Collectors.joining(","));
  }
}
