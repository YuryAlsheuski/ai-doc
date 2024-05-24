package org.ai.doc.provider.ollama.provider;

import org.ai.doc.common.model.domain.Model;
import org.ai.doc.provider.common.converter.ModelOptionConverter;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.model.ModelOptions;
import org.springframework.ai.ollama.OllamaEmbeddingClient;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Component;

@Component
final class OllamaEmbeddingClientProvider extends OllamaClientProvider<EmbeddingClient> {

  OllamaEmbeddingClientProvider(OllamaApi api, ModelOptionConverter<OllamaOptions> converter) {
    super(api, converter);
  }

  @Override
  public EmbeddingClient getClient(Model model, ModelOptions options) {
    var embeddingOptions = converter.convert(options).withModel(model.getName());
    return new OllamaEmbeddingClient(api).withDefaultOptions(embeddingOptions);
  }
}
