package org.ai.doc.model.factory;

import java.util.Set;
import org.ai.doc.model.domain.EngineType;
import org.ai.doc.model.domain.Model;
import org.ai.doc.model.domain.ModelType;

public interface ModelFactory {
  Model getModel(EngineType engineType, ModelType modelType, String modelName);

  Model getModel(EngineType engineType, ModelType modelType);

  Set<Model> getAll();
}
