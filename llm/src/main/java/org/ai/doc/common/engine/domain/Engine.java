package org.ai.doc.common.engine.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Engine {
  private final EngineType type;
  private final String url;
}
