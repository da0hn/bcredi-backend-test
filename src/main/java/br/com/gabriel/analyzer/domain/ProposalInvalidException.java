package br.com.gabriel.analyzer.domain;

import java.io.Serial;
import java.io.Serializable;

public class ProposalInvalidException extends RuntimeException implements Serializable {

  @Serial private static final long serialVersionUID = 7823891387130318055L;

  public ProposalInvalidException() {
  }

  public ProposalInvalidException(final String message) {
    super(message);
  }
}
