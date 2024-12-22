package org.ai.doc.model.factory;

import org.ai.doc.model.domain.Engine;
import org.ai.doc.model.domain.EngineType;

public interface EngineFactory {
  Engine getEngine(EngineType engineType);
}
