package br.com.gabriel.analyzer.domain;

public interface LoanInstallmentMinimumValueCalculator {

  double calculate(final double value);

  boolean inRange(final int age);
}
