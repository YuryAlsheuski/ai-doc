package org.ai.doc.model.openai;

import static org.ai.doc.common.constant.Action.IMAGE_DESCRIPTION;
import static org.ai.doc.common.constant.Action.TEXT_GENERATION;
import static org.ai.doc.common.constant.EngineType.OPEN_AI;

import java.util.Set;
import org.ai.doc.common.constant.Action;
import org.ai.doc.common.constant.EngineType;
import org.ai.doc.common.request.LLMRequest;
import org.ai.doc.model.AIModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
class OpenAiChatModelCustom extends OpenAiChatModel implements AIModel<ChatResponse> {

  OpenAiChatModelCustom(OpenAiApi openAiApi) {
    super(openAiApi);
  }

  @Override
  public ChatResponse call(LLMRequest request) {
    return super.call(request.toChatRequest(OpenAiChatOptions.class));
  }

  @Override
  public Flux<ChatResponse> stream(LLMRequest request) {
    return super.stream(request.toChatRequest(OpenAiChatOptions.class));
  }

  @Override
  public EngineType getEngineType() {
    return OPEN_AI;
  }

  public Set<Action> getSupportedActions() {
    return Set.of(TEXT_GENERATION, IMAGE_DESCRIPTION);
  }
}
