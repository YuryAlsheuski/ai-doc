package org.ai.doc.common.model.factory;

import org.ai.doc.common.engine.domain.EngineType;
import org.ai.doc.common.model.domain.Model;
import org.ai.doc.common.model.domain.ModelType;

public interface ModelFactory {
  Model getModel(EngineType engineType, ModelType modelType, String modelName);

  Model getModel(EngineType engineType, ModelType modelType);
}
