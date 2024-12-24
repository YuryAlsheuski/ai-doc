package org.ai.doc.common.request;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.ai.doc.model.domain.Action;
import org.ai.doc.model.domain.EngineType;
import org.modelmapper.ModelMapper;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.embedding.EmbeddingOptions;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.model.ModelOptions;

@Getter
@RequiredArgsConstructor
public class LLMRequest {

  private final UserMessage message;
  private final ModelOptions modelOptions;
  private final EngineType engine;
  private final Action action;

  public <T extends ChatOptions> Prompt toChatRequest(Class<T> options) {
    return new Prompt(message, castOptions(options));
  }

  public <T extends EmbeddingOptions> EmbeddingRequest toEmbeddingRequest(Class<T> options) {
    return new EmbeddingRequest(List.of(message.getContent()), castOptions(options));
  }

  public <T extends ModelOptions> T castOptions(Class<T> clazz) {
    return new ModelMapper().map(modelOptions, clazz);
  }
}
