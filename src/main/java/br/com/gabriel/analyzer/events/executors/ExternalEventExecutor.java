package br.com.gabriel.analyzer.events.executors;

import br.com.gabriel.analyzer.events.Event;

import java.util.List;

public interface ExternalEventExecutor {

  void execute(List<Event> events);

}
