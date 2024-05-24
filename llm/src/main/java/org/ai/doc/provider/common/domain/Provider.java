package org.ai.doc.provider.common.domain;

import org.ai.doc.common.domain.EngineType;
import org.springframework.ai.model.ModelClient;
import org.springframework.ai.model.ModelOptions;

public interface Provider<T extends ModelClient<?, ?>> {

  T getClient(ModelOptions options);

  EngineType getEngineType();
}
