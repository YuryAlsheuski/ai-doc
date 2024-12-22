package org.ai.doc.core.domain;

import static org.ai.doc.model.domain.ModelType.IMAGE_DESCRIPTION;

import lombok.Getter;
import org.ai.doc.model.domain.ModelType;

@Getter
public enum ContentType {
  IMAGE(IMAGE_DESCRIPTION);

  private final ModelType modelType;

  ContentType(ModelType modelType) {
    this.modelType = modelType;
  }
}
