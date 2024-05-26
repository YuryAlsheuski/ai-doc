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
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.AbstractEmbeddingClient;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.model.ModelOptions;
import org.springframework.ai.ollama.OllamaEmbeddingClient;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
final class OllamaEmbeddingBaseClient extends AbstractEmbeddingClient
    implements Client<List<Double>> {

  private final OllamaApi api;
  private final ModelMapper modelMapper;

  @Override
  public List<Double> call(Prompt prompt, ModelOptions modelOptions) {
    var options = modelMapper.map(modelOptions, OllamaOptions.class);
    var request = new EmbeddingRequest(List.of(prompt.getContents()), options);
    return call(request).getResult().getOutput();
  }

  @Override
  public EmbeddingResponse call(EmbeddingRequest request) {
    var options = request.getOptions();
    if (!(options instanceof OllamaOptions)) {
      throw new RuntimeException(); // todo target exception
    }
    return new OllamaEmbeddingClient(api).withDefaultOptions((OllamaOptions) options).call(request);
  }

  @Override
  public List<Double> embed(Document document) {
    return List.of();
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
