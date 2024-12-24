package org.ai.doc.client.engine.ollama.client;

import static org.ai.doc.model.domain.Action.IMAGE_DESCRIPTION;
import static org.ai.doc.model.domain.Action.TEXT_GENERATION;
import static org.ai.doc.model.domain.EngineType.OLLAMA;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.ai.doc.client.domain.Client;
import org.ai.doc.model.domain.Action;
import org.ai.doc.model.domain.EngineType;
import org.modelmapper.ModelMapper;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.ModelOptions;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
final class OllamaChatClient implements Client<ChatResponse> {

  private final OllamaApi api;
  private final ModelMapper modelMapper;

  @Override
  public ChatResponse call(Prompt prompt, ModelOptions modelOptions) {
    return createClient(modelOptions).call(prompt);
  }

  @Override
  public Flux<ChatResponse> stream(Prompt prompt, ModelOptions modelOptions) {
    return createClient(modelOptions).stream(prompt);
  }

  @Override
  public EngineType getEngineType() {
    return OLLAMA;
  }

  @Override
  public Set<Action> getSupportedModelTypes() {
    return Set.of(TEXT_GENERATION, IMAGE_DESCRIPTION);
  }

  private OllamaChatModel createClient(ModelOptions modelOptions) {
    var options = modelMapper.map(modelOptions, OllamaOptions.class);
    return new OllamaChatModel(api, options);
  }
}
