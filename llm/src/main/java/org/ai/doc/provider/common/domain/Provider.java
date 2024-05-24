package org.ai.doc.provider.common.domain;

import org.ai.doc.common.engine.domain.EngineType;
import org.ai.doc.common.model.domain.Model;
import org.springframework.ai.model.ModelClient;
import org.springframework.ai.model.ModelOptions;

public interface Provider<T extends ModelClient<?, ?>> {

  T getClient(Model model, ModelOptions options);

  EngineType getEngineType();
}
