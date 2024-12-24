package org.ai.doc.core.domain;

import static org.ai.doc.model.domain.Action.IMAGE_DESCRIPTION;

import lombok.Getter;
import org.ai.doc.model.domain.Action;

@Getter
public enum ContentType {
  IMAGE(IMAGE_DESCRIPTION);

  private final Action action;

  ContentType(Action action) {
    this.action = action;
  }
}
