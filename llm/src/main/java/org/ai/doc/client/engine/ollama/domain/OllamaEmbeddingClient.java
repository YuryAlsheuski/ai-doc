package org.ai.doc.client.engine.ollama.domain;

import static org.ai.doc.common.engine.domain.EngineType.OLLAMA;
import static org.ai.doc.common.model.domain.ModelType.TEXT_EMBEDDING;

import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.ai.doc.client.domain.Client;
import org.ai.doc.common.engine.domain.EngineType;
import org.ai.doc.common.model.domain.ModelType;
import org.modelmapper.ModelMapper;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.model.ModelOptions;
import org.springframework.ai.ollama.OllamaEmbeddingModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
final class OllamaEmbeddingClient implements Client<EmbeddingResponse> {

  private final OllamaApi api;
  private final ModelMapper modelMapper;

  @Override
  public EmbeddingResponse call(Prompt prompt, ModelOptions modelOptions) {
    var options = modelMapper.map(modelOptions, OllamaOptions.class);
    var request = new EmbeddingRequest(List.of(prompt.getContents()), options);
    return new OllamaEmbeddingModel(api, options).call(request);
  }

  @Override
  public Flux<EmbeddingResponse> stream(Prompt prompt, ModelOptions modelOptions) {
    throw new UnsupportedOperationException("Stream API unsupported for embedding");
  }

  @Override
  public EngineType getEngineType() {
    return OLLAMA;
  }

  @Override
  public Set<ModelType> getSupportedModelTypes() {
    return Set.of(TEXT_EMBEDDING);
  }
}
