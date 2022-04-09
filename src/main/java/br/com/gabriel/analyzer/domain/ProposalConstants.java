package br.com.gabriel.analyzer.domain;

public final class ProposalConstants {


  public static final int ONE_MONTH = 1;
  public static final int ONE_YEAR = 12 * ONE_MONTH;
  public static final int MIN_YEAR_INSTALLMENTS = ONE_YEAR * 2;
  public static final int MAX_YEAR_INSTALLMENTS = ONE_YEAR * 15;
  public static final double MIN_LOAN_VALUE = 30_000;
  public static final double MAX_LOAN_VALUE = 3_000_000;
  public static final int MIN_AGE = 18;

  private ProposalConstants() {
  }

}
