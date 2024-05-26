package org.ai.doc.common.domain;

import org.ai.doc.common.engine.domain.EngineType;
import org.ai.doc.common.model.domain.ModelType;

public interface LLMItem {
  EngineType getEngineType();

  ModelType getModelType();
}
