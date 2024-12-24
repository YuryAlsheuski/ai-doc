package org.ai.doc.testmodel.openai;

import static org.ai.doc.model.domain.Action.IMAGE_DESCRIPTION;
import static org.ai.doc.model.domain.Action.TEXT_GENERATION;
import static org.ai.doc.model.domain.EngineType.OPEN_AI;

import java.util.Set;
import org.ai.doc.common.request.LLMRequest;
import org.ai.doc.model.domain.Action;
import org.ai.doc.model.domain.EngineType;
import org.ai.doc.testmodel.AIModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class OpenAiChatModelCustom extends OpenAiChatModel implements AIModel<ChatResponse> {

  public OpenAiChatModelCustom(OpenAiApi openAiApi) {
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
