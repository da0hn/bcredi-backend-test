package br.com.gabriel.analyzer.domain;

import java.util.stream.Stream;

public enum MultiplierFactorAgeBased implements LoanInstallmentMinimumValueCalculator {
  FOUR_TIMES {
    @Override public double calculate(final double value) {
      return 4 * value;
    }

    @Override public boolean inRange(final int age) {
      return age >= 18 && age < 24;
    }

  },
  THREE_TIMES {
    @Override public double calculate(final double value) {
      return 3 * value;
    }

    @Override public boolean inRange(final int age) {
      return age >= 24 && age < 50;
    }
  },
  TWO_TIMES {
    @Override public double calculate(final double value) {
      return 2 * value;
    }

    @Override public boolean inRange(final int age) {
      return age >= 50;
    }
  };

  public static LoanInstallmentMinimumValueCalculator fromAge(final int age) {
    return Stream.of(values())
      .filter(limit -> limit.inRange(age))
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException("Limit for age " + age + " is invalid"));
  }

}
