package org.ai.doc.provider.openai.factory;

import lombok.RequiredArgsConstructor;
import org.ai.doc.provider.common.domain.ProviderType;
import org.ai.doc.provider.common.factory.ProviderFactory;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.openai.OpenAiEmbeddingClient;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
final class OpenAiEmbeddingClientFactory extends ProviderFactory<EmbeddingClient> {

  private final OpenAiApi api;

  @Override
  public EmbeddingClient getObject() {
    return new OpenAiEmbeddingClient(api);
  }

  @Override
  public Class<EmbeddingClient> getObjectType() {
    return EmbeddingClient.class;
  }

  @Override
  public ProviderType getProviderType() {
    return ProviderType.OPEN_AI;
  }
}
