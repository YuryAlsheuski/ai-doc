package org.ai.doc.common.engine.factory;

import org.ai.doc.common.engine.domain.Engine;
import org.ai.doc.common.engine.domain.EngineType;

public interface EngineFactory {
  Engine getEngine(EngineType engineType);
}
