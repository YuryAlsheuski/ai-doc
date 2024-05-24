package org.ai.doc.common.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Model {
  private final EngineType engine;
  private final ModelType type;
  private final String name;
}
