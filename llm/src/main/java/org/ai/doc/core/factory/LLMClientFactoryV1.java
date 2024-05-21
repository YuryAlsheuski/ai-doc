package org.ai.doc.core.factory;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.ai.doc.core.domain.LLMClient;
import org.ai.doc.provider.common.domain.ProviderType;
import org.ai.doc.provider.common.factory.ProviderFactory;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
final class LLMClientFactoryV1 implements LLMClientFactory {

  private final Map<ProviderType, ProviderFactory<EmbeddingClient>> embeddingClientFactories;
  private final Map<ProviderType, ProviderFactory<ChatClient>> chatClientFactories;

  @Override
  public LLMClient<String> getChatClient(ProviderType type) {
    return null;
  }

  @Override
  public LLMClient<List<Double>> getEmbeddingClient(ProviderType type) {
    return (prompt, options) -> {
      try {
        return embeddingClientFactories.get(type).getObject().embed(prompt.getContents());
      } catch (Exception e) {
        throw new RuntimeException(e); // todo personal exception here
      }
    };
  }
}
