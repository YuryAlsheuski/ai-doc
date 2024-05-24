package org.ai.doc.common.model.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.ai.doc.common.engine.domain.EngineType;

@Getter
@RequiredArgsConstructor
public class Model {
  private final EngineType engine;
  private final ModelType type;
  private final String name;
}
