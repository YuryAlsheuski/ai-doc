package org.ai.doc.model.ollama;

import static org.ai.doc.common.constant.Action.IMAGE_DESCRIPTION;
import static org.ai.doc.common.constant.Action.TEXT_GENERATION;
import static org.ai.doc.common.constant.EngineType.OLLAMA;

import java.util.Set;
import org.ai.doc.common.constant.Action;
import org.ai.doc.common.constant.EngineType;
import org.ai.doc.common.request.LLMRequest;
import org.ai.doc.model.AIModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
class OllamaChatModelCustom extends OllamaChatModel implements AIModel<ChatResponse> {

  OllamaChatModelCustom(OllamaApi chatApi) {
    super(chatApi);
  }

  @Override
  public ChatResponse call(LLMRequest request) {
    return super.call(request.toChatRequest(OllamaOptions.class));
  }

  @Override
  public Flux<ChatResponse> stream(LLMRequest request) {
    return super.stream(request.toChatRequest(OllamaOptions.class));
  }

  @Override
  public EngineType getEngineType() {
    return OLLAMA;
  }

  public Set<Action> getSupportedActions() {
    return Set.of(TEXT_GENERATION, IMAGE_DESCRIPTION);
  }
}
