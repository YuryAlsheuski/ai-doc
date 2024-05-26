package org.ai.doc.client.engine.openai.domain;

import static org.ai.doc.common.engine.domain.EngineType.OPEN_AI;
import static org.ai.doc.common.model.domain.ModelType.TEXT_EMBEDDING;
import static org.springframework.ai.document.MetadataMode.EMBED;
import static org.springframework.ai.retry.RetryUtils.DEFAULT_RETRY_TEMPLATE;

import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.ai.doc.client.domain.Client;
import org.ai.doc.common.engine.domain.EngineType;
import org.ai.doc.common.model.domain.ModelType;
import org.modelmapper.ModelMapper;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.AbstractEmbeddingClient;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.model.ModelOptions;
import org.springframework.ai.openai.OpenAiEmbeddingClient;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
final class OpenAiEmbeddingBaseClient extends AbstractEmbeddingClient
    implements Client<EmbeddingResponse> {

  private final OpenAiApi api;
  private final ModelMapper modelMapper;

  @Override
  public EmbeddingResponse call(Prompt prompt, ModelOptions modelOptions) {
    var options = modelMapper.map(modelOptions, OpenAiEmbeddingOptions.class);
    var request = new EmbeddingRequest(List.of(prompt.getContents()), options);
    return call(request);
  }

  @Override
  public Flux<EmbeddingResponse> stream(Prompt prompt, ModelOptions modelOptions) {
    throw new UnsupportedOperationException("Stream API unsupported for embedding");
  }

  @Override
  public EmbeddingResponse call(EmbeddingRequest request) {
    var options = request.getOptions();
    if (!(options instanceof OpenAiEmbeddingOptions)) {
      throw new RuntimeException(); // todo target exception
    }
    return new OpenAiEmbeddingClient(
            api, EMBED, (OpenAiEmbeddingOptions) options, DEFAULT_RETRY_TEMPLATE)
        .call(request);
  }

  @Override
  public List<Double> embed(Document document) {
    return List.of();
  }

  @Override
  public EngineType getEngineType() {
    return OPEN_AI;
  }

  @Override
  public Set<ModelType> getSupportedModelTypes() {
    return Set.of(TEXT_EMBEDDING);
  }
}
