package org.ai.doc.model.domain;

import lombok.Data;

@Data
public class Engine {
  private final EngineType type;
  private final String url;
}
