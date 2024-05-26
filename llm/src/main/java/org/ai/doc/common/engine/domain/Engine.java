package org.ai.doc.common.engine.domain;

import lombok.Data;

@Data
public class Engine {
  private final EngineType type;
  private final String url;
}
