package org.ai.doc.core.factory;

import java.util.List;
import org.ai.doc.common.domain.EngineType;
import org.ai.doc.core.domain.LLMClient;
import org.springframework.ai.model.ModelOptions;

public interface ClientFactory {
  LLMClient<String> getChatClient(EngineType type, ModelOptions modelOptions);

  LLMClient<List<Double>> getEmbeddingClient(EngineType type, ModelOptions modelOptions);
}
