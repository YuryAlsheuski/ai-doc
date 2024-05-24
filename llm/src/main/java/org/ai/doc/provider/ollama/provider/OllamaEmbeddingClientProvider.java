package org.ai.doc.provider.ollama.provider;

import org.ai.doc.provider.common.converter.ModelOptionConverter;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.model.ModelOptions;
import org.springframework.ai.ollama.OllamaEmbeddingClient;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Component;

@Component
class OllamaEmbeddingClientProvider extends OllamaClientProvider<EmbeddingClient> {

  OllamaEmbeddingClientProvider(OllamaApi api, ModelOptionConverter<OllamaOptions> converter) {
    super(api, converter);
  }

  @Override
  public EmbeddingClient getClient(ModelOptions options) {
    return new OllamaEmbeddingClient(api).withDefaultOptions(converter.convert(options));
  }
}
