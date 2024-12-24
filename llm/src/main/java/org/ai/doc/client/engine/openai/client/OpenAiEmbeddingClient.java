package org.ai.doc.client.engine.openai.client;

import static org.ai.doc.model.domain.EngineType.OPEN_AI;
import static org.ai.doc.model.domain.Action.TEXT_EMBEDDING;
import static org.springframework.ai.document.MetadataMode.EMBED;
import static org.springframework.ai.retry.RetryUtils.DEFAULT_RETRY_TEMPLATE;

import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.ai.doc.client.domain.Client;
import org.ai.doc.model.domain.EngineType;
import org.ai.doc.model.domain.Action;
import org.modelmapper.ModelMapper;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.model.ModelOptions;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
final class OpenAiEmbeddingClient implements Client<EmbeddingResponse> {

  private final OpenAiApi api;
  private final ModelMapper modelMapper;

  @Override
  public EmbeddingResponse call(Prompt prompt, ModelOptions modelOptions) {
    var options = modelMapper.map(modelOptions, OpenAiEmbeddingOptions.class);
    var request = new EmbeddingRequest(List.of(prompt.getContents()), options);
    return new OpenAiEmbeddingModel(api, EMBED, options, DEFAULT_RETRY_TEMPLATE).call(request);
  }

  @Override
  public Flux<EmbeddingResponse> stream(Prompt prompt, ModelOptions modelOptions) {
    throw new UnsupportedOperationException("Stream API unsupported for embedding");
  }

  @Override
  public EngineType getEngineType() {
    return OPEN_AI;
  }

  @Override
  public Set<Action> getSupportedModelTypes() {
    return Set.of(TEXT_EMBEDDING);
  }
}
