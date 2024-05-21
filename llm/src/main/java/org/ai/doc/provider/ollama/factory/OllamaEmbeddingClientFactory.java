package org.ai.doc.provider.ollama.factory;

import lombok.RequiredArgsConstructor;
import org.ai.doc.provider.common.domain.ProviderType;
import org.ai.doc.provider.common.factory.ProviderFactory;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.ollama.OllamaEmbeddingClient;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
final class OllamaEmbeddingClientFactory extends ProviderFactory<EmbeddingClient> {

  private final OllamaApi api;

  @Override
  public EmbeddingClient getObject() {
    return new OllamaEmbeddingClient(api);
  }

  @Override
  public ProviderType getProviderType() {
    return ProviderType.OLLAMA;
  }

  @Override
  public Class<EmbeddingClient> getObjectType() {
    return EmbeddingClient.class;
  }
}
