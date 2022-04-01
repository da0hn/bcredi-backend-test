package br.com.gabriel.analyzer.utils;

import java.util.Spliterator;
import java.util.function.Consumer;

public final class SpliteratorActionApplier {

  private SpliteratorActionApplier() {
  }

  @SafeVarargs public static boolean apply(final Spliterator<String> data, final Consumer<String>... actions) {
    for(final var action : actions) {
      final boolean isAdvanced = data.tryAdvance(action);
      if(!isAdvanced) return false;
    }
    return true;
  }

}
