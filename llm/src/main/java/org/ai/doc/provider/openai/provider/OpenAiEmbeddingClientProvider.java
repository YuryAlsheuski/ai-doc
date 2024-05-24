package org.ai.doc.provider.openai.provider;

import static org.springframework.ai.document.MetadataMode.EMBED;
import static org.springframework.ai.retry.RetryUtils.DEFAULT_RETRY_TEMPLATE;

import org.ai.doc.common.domain.Model;
import org.ai.doc.provider.common.converter.ModelOptionConverter;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.model.ModelOptions;
import org.springframework.ai.openai.OpenAiEmbeddingClient;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Component;

@Component
final class OpenAiEmbeddingClientProvider extends OpenAiClientProvider<EmbeddingClient> {

  private final ModelOptionConverter<OpenAiEmbeddingOptions> converter;

  OpenAiEmbeddingClientProvider(
      OpenAiApi api, ModelOptionConverter<OpenAiEmbeddingOptions> converter) {
    super(api);
    this.converter = converter;
  }

  @Override
  public EmbeddingClient getClient(Model model, ModelOptions options) {
    var embeddingOptions = converter.convert(options);
    embeddingOptions.setModel(model.getName());
    return new OpenAiEmbeddingClient(api, EMBED, embeddingOptions, DEFAULT_RETRY_TEMPLATE);
  }
}
