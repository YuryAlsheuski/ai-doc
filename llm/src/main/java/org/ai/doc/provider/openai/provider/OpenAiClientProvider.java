package org.ai.doc.provider.openai.provider;

import static org.ai.doc.common.domain.EngineType.OPEN_AI;

import lombok.RequiredArgsConstructor;
import org.ai.doc.common.domain.EngineType;
import org.ai.doc.provider.common.domain.Provider;
import org.springframework.ai.model.ModelClient;
import org.springframework.ai.openai.api.OpenAiApi;

@RequiredArgsConstructor
abstract class OpenAiClientProvider<T extends ModelClient<?, ?>> implements Provider<T> {

  protected final OpenAiApi api;

  @Override
  public EngineType getEngineType() {
    return OPEN_AI;
  }
}
