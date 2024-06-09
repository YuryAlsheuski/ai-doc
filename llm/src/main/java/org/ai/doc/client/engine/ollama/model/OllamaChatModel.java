package org.ai.doc.client.engine.ollama.model;

import static org.springframework.ai.ollama.api.OllamaApi.Message.Role.USER;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.ai.doc.client.common.response.SimpleChatResponse;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaApi.ChatRequest;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;

@Component
@Scope(SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class OllamaChatModel {

  private final OllamaApi api;
  private final OllamaOptions modelOptions;

  public ChatResponse call(Prompt prompt) {

    var request = buildRequest(prompt, false);
    var response = api.chat(request);

    return new SimpleChatResponse(response.done(), response.message().content());
  }

  public Flux<ChatResponse> stream(Prompt prompt) {

    var request = buildRequest(prompt, true);
    var response = api.streamingChat(request);

    return response.map(chunk -> new SimpleChatResponse(chunk.done(), chunk.message().content()));
  }

  private ChatRequest buildRequest(Prompt prompt, boolean streaming) {
    var ollamaMessages = prompt.getInstructions().stream().map(this::convertMessage).toList();

    return ChatRequest.builder(modelOptions.getModel())
        .withOptions(modelOptions)
        .withMessages(ollamaMessages)
        .withStream(streaming)
        .build();
  }

  private OllamaApi.Message convertMessage(Message message) {
    var messageBuilder = OllamaApi.Message.builder(USER).withContent(message.getContent());

    if (!CollectionUtils.isEmpty(message.getMedia())) {
      messageBuilder.withImages(
          message.getMedia().stream().map(media -> fromMediaData(media.getData())).toList());
    }

    return messageBuilder.build();
  }

  private String fromMediaData(Object mediaData) {
    if (mediaData instanceof byte[] bytes) {
      return Base64.getEncoder().encodeToString(bytes);
    }
    if (mediaData instanceof String text) {
      return text;
    }
    throw new IllegalArgumentException(
        "Unsupported media data type: " + mediaData.getClass().getSimpleName());
  }
}
