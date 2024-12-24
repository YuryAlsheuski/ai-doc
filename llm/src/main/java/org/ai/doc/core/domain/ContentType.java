package org.ai.doc.core.domain;

import static org.ai.doc.common.constant.Action.IMAGE_DESCRIPTION;

import lombok.Getter;
import org.ai.doc.common.constant.Action;

@Getter
public enum ContentType {
  IMAGE(IMAGE_DESCRIPTION);

  private final Action action;

  ContentType(Action action) {
    this.action = action;
  }
}
