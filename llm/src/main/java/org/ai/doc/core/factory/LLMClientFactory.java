package org.ai.doc.core.factory;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.ai.doc.common.domain.EngineType;
import org.ai.doc.common.domain.Model;
import org.ai.doc.core.domain.LLMClient;
import org.ai.doc.provider.common.domain.Provider;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.model.ModelOptions;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
final class LLMClientFactory implements ClientFactory {

  private final Map<EngineType, Provider<EmbeddingClient>> typeToEmbeddingProviders;
  private final Map<EngineType, Provider<ChatClient>> typeToChatProviders;

  @Override
  public LLMClient<String> getChatClient(Model model, ModelOptions modelOptions) {
    return null;
  }

  @Override
  public LLMClient<List<Double>> getEmbeddingClient(Model model, ModelOptions modelOptions) {
    return (prompt) -> {
      try {
        return typeToEmbeddingProviders
            .get(model.getEngine())
            .getClient(model, modelOptions)
            .embed(prompt.getContents());
      } catch (Exception e) {
        throw new RuntimeException(e); // todo personal exception here
      }
    };
  }
}
