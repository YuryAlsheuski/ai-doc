package org.ai.doc.client.engine.ollama.domain;

import static org.ai.doc.common.engine.domain.EngineType.OLLAMA;
import static org.ai.doc.common.model.domain.ModelType.IMAGE_DESCRIPTION;
import static org.ai.doc.common.model.domain.ModelType.TEXT_GENERATION;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.ai.doc.client.domain.Client;
import org.ai.doc.common.engine.domain.EngineType;
import org.ai.doc.common.model.domain.ModelType;
import org.modelmapper.ModelMapper;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.ModelOptions;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
final class OllamaChatBaseClient implements ChatClient, Client<String> {

  private final OllamaApi api;
  private final ModelMapper modelMapper;

  @Override
  public String call(Prompt prompt, ModelOptions modelOptions) {
    return "";
  }

  @Override
  public ChatResponse call(Prompt prompt) {
    return null;
  }

  @Override
  public EngineType getEngineType() {
    return OLLAMA;
  }

  @Override
  public Set<ModelType> getSupportedModelTypes() {
    return Set.of(TEXT_GENERATION, IMAGE_DESCRIPTION);
  }
}
