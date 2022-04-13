package br.com.gabriel.analyzer.domain;

import java.io.Serial;
import java.io.Serializable;
import java.text.MessageFormat;

public class ProposalInvalidException extends RuntimeException implements Serializable {

  @Serial private static final long serialVersionUID = 7823891387130318055L;

  public ProposalInvalidException() {
  }

  public ProposalInvalidException(final String message) {
    super(message);
  }

  public ProposalInvalidException(final String message, final Object entity) {
    super(MessageFormat.format("Cause: {0} | {1}", entity, message));
  }
}
