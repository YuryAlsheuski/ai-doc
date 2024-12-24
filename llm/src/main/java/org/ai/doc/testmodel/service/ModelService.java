package org.ai.doc.testmodel.service;

import org.ai.doc.model.domain.Action;
import org.ai.doc.model.domain.EngineType;
import org.ai.doc.testmodel.AIModel;

public interface ModelService {

  AIModel<?> getModel(EngineType engineType, Action action, String modelName);

  String getDefaultName(EngineType engineType, Action action);
}
