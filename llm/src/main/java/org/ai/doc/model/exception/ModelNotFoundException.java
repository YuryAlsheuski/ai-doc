package org.ai.doc.model.exception;

public class ModelNotFoundException extends RuntimeException {
  public ModelNotFoundException(String msg) {
    super(msg);
  }
}
