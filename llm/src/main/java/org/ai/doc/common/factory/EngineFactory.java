package org.ai.doc.common.factory;

import org.ai.doc.common.domain.Engine;
import org.ai.doc.common.domain.EngineType;

public interface EngineFactory {
  Engine getEngine(EngineType engineType);
}
