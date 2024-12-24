package org.ai.doc.model.service;

import org.ai.doc.common.constant.Action;
import org.ai.doc.common.constant.EngineType;
import org.ai.doc.model.AIModel;

public interface ModelService {

  AIModel<?> getModel(EngineType engineType, Action action, String modelName);

  String getDefaultName(EngineType engineType, Action action);
}
