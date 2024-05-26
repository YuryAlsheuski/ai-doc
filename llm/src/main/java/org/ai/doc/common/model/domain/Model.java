package org.ai.doc.common.model.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.ai.doc.common.domain.LLMItem;
import org.ai.doc.common.engine.domain.EngineType;

@RequiredArgsConstructor
@EqualsAndHashCode
public class Model implements LLMItem {
  private final EngineType engine;
  private final ModelType type;
  @Getter private final String name;

  @Override
  public EngineType getEngineType() {
    return engine;
  }

  @Override
  public ModelType getModelType() {
    return type;
  }
}
