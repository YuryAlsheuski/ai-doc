package org.ai.doc.client.domain;

import java.util.Set;
import org.ai.doc.common.engine.domain.EngineType;
import org.ai.doc.common.model.domain.ModelType;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.ModelOptions;

public interface Client<T> {
  T call(Prompt prompt, ModelOptions modelOptions);

  EngineType getEngineType();

  Set<ModelType> getSupportedModelTypes();
}
