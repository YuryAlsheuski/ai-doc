package org.ai.doc.core.factory;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.ai.doc.core.domain.LLMClient;
import org.ai.doc.provider.common.domain.Provider;
import org.ai.doc.provider.common.domain.ProviderType;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.model.ModelOptions;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
final class LLMClientFactoryV1 implements LLMClientFactory {

  private final Map<ProviderType, Provider<EmbeddingClient>> typeToEmbeddingProviders;
  private final Map<ProviderType, Provider<ChatClient>> typeToChatProviders;

  @Override
  public LLMClient<String> getChatClient(ProviderType type, ModelOptions modelOptions) {
    return null;
  }

  @Override
  public LLMClient<List<Double>> getEmbeddingClient(ProviderType type, ModelOptions modelOptions) {
    return (prompt) -> {
      try {
        return typeToEmbeddingProviders
            .get(type)
            .getClient(modelOptions)
            .embed(prompt.getContents());
      } catch (Exception e) {
        throw new RuntimeException(e); // todo personal exception here
      }
    };
  }
}
