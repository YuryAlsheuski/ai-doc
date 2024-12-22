package org.ai.doc.model.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class Model {
  private final EngineType engine;
  private final ModelType type;
  private final String name;
}
