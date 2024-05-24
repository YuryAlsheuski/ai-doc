package org.ai.doc.provider.ollama.provider;

import static org.ai.doc.common.engine.domain.EngineType.OLLAMA;

import lombok.RequiredArgsConstructor;
import org.ai.doc.common.engine.domain.EngineType;
import org.ai.doc.provider.common.converter.ModelOptionConverter;
import org.ai.doc.provider.common.domain.Provider;
import org.springframework.ai.model.ModelClient;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;

@RequiredArgsConstructor
abstract class OllamaClientProvider<T extends ModelClient<?, ?>> implements Provider<T> {

  protected final OllamaApi api;
  protected final ModelOptionConverter<OllamaOptions> converter;

  @Override
  public EngineType getEngineType() {
    return OLLAMA;
  }
}
