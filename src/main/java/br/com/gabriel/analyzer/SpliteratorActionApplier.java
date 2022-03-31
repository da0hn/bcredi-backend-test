package br.com.gabriel.analyzer;

import java.util.Spliterator;
import java.util.function.Consumer;

public final class SpliteratorActionApplier {

  private SpliteratorActionApplier() {
  }

  @SafeVarargs public static boolean apply(Spliterator<String> data, Consumer<String>... actions) {
    for(var action : actions) {
      boolean isAdvanced = data.tryAdvance(action);
      if(!isAdvanced) return false;
    }
    return true;
  }

}
