package org.ai.doc.common.factory.model;

import org.ai.doc.common.domain.EngineType;
import org.ai.doc.common.domain.Model;
import org.ai.doc.common.domain.ModelType;

public interface ModelFactory {
  Model getModel(EngineType engineType, ModelType modelType, String modelName);

  Model getModel(EngineType engineType, ModelType modelType);
}
