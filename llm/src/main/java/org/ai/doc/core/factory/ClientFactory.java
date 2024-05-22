package org.ai.doc.core.factory;

import java.util.List;
import org.ai.doc.core.domain.LLMClient;
import org.ai.doc.provider.common.domain.ProviderType;
import org.springframework.ai.model.ModelOptions;

public interface ClientFactory {
  LLMClient<String> getChatClient(ProviderType type, ModelOptions modelOptions);

  LLMClient<List<Double>> getEmbeddingClient(ProviderType type, ModelOptions modelOptions);
}
