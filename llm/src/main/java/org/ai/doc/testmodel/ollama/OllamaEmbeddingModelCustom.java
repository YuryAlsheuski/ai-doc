package org.ai.doc.testmodel.ollama;

import static org.ai.doc.model.domain.Action.TEXT_EMBEDDING;
import static org.ai.doc.model.domain.EngineType.OLLAMA;

import java.util.Set;
import org.ai.doc.common.request.LLMRequest;
import org.ai.doc.model.domain.Action;
import org.ai.doc.model.domain.EngineType;
import org.ai.doc.testmodel.AIModel;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.ollama.OllamaEmbeddingModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class OllamaEmbeddingModelCustom extends OllamaEmbeddingModel
    implements AIModel<EmbeddingResponse> {

  public OllamaEmbeddingModelCustom(OllamaApi ollamaApi) {
    super(ollamaApi);
  }

  @Override
  public EmbeddingResponse call(LLMRequest request) {
    return super.call(request.toEmbeddingRequest(OllamaOptions.class));
  }

  @Override
  public Flux<EmbeddingResponse> stream(LLMRequest request) {
    throw new UnsupportedOperationException("Stream API unsupported for embedding");
  }

  @Override
  public EngineType getEngineType() {
    return OLLAMA;
  }

  @Override
  public Set<Action> getSupportedActions() {
    return Set.of(TEXT_EMBEDDING);
  }
}
