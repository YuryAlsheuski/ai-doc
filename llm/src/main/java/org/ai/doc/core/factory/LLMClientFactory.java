package org.ai.doc.core.factory;

import java.util.List;
import org.ai.doc.core.domain.LLMClient;
import org.ai.doc.provider.common.domain.ProviderType;

public interface LLMClientFactory {
  LLMClient<String> getChatClient(ProviderType type);

  LLMClient<List<Double>> getEmbeddingClient(ProviderType type);
}
