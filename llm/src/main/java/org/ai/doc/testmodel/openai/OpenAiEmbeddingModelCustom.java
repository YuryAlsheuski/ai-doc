package org.ai.doc.testmodel.openai;

import static org.ai.doc.model.domain.Action.TEXT_EMBEDDING;
import static org.ai.doc.model.domain.EngineType.OPEN_AI;

import java.util.Set;
import org.ai.doc.common.request.LLMRequest;
import org.ai.doc.model.domain.Action;
import org.ai.doc.model.domain.EngineType;
import org.ai.doc.testmodel.AIModel;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class OpenAiEmbeddingModelCustom extends OpenAiEmbeddingModel
    implements AIModel<EmbeddingResponse> {

  public OpenAiEmbeddingModelCustom(OpenAiApi openAiApi) {
    super(openAiApi);
  }

  @Override
  public EmbeddingResponse call(LLMRequest request) {
    return super.call(request.toEmbeddingRequest(OpenAiEmbeddingOptions.class));
  }

  @Override
  public Flux<EmbeddingResponse> stream(LLMRequest request) {
    throw new UnsupportedOperationException("Stream API unsupported for embedding");
  }

  @Override
  public EngineType getEngineType() {
    return OPEN_AI;
  }

  @Override
  public Set<Action> getSupportedActions() {
    return Set.of(TEXT_EMBEDDING);
  }
}
