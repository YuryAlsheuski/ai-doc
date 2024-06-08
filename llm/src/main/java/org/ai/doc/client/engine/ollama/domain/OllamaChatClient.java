package org.ai.doc.client.engine.ollama.domain;

import static org.ai.doc.common.engine.domain.EngineType.OLLAMA;
import static org.ai.doc.common.model.domain.ModelType.IMAGE_DESCRIPTION;
import static org.ai.doc.common.model.domain.ModelType.TEXT_GENERATION;
import static org.springframework.ai.ollama.api.OllamaApi.Message.Role.USER;

import java.util.Base64;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.ai.doc.client.common.response.StreamingChatResponse;
import org.ai.doc.client.domain.Client;
import org.ai.doc.common.engine.domain.EngineType;
import org.ai.doc.common.model.domain.ModelType;
import org.modelmapper.ModelMapper;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.ModelOptions;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
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
  public Set<ModelType> getSupportedModelTypes() {
    return Set.of(TEXT_GENERATION, IMAGE_DESCRIPTION);
  }

  private OllamaCustomChatModel createClient(ModelOptions modelOptions) {
    var options = modelMapper.map(modelOptions, OllamaOptions.class);
    return new OllamaCustomChatModel(api, options);
  }

  private static class OllamaCustomChatModel extends OllamaChatModel {

    private final OllamaApi api;
    private final OllamaOptions modelOptions;

    public OllamaCustomChatModel(OllamaApi chatApi, OllamaOptions defaultOptions) {
      super(chatApi, defaultOptions);
      this.api = chatApi;
      this.modelOptions = defaultOptions;
    }

    @Override
    public Flux<ChatResponse> stream(Prompt prompt) {

      var ollamaMessages = prompt.getInstructions().stream().map(this::convertMessage).toList();

      var request =
          OllamaApi.ChatRequest.builder(modelOptions.getModel())
              .withOptions(modelOptions)
              .withMessages(ollamaMessages)
              .withStream(true)
              .build();

      var response = api.streamingChat(request);

      return response.map(
          chunk -> new StreamingChatResponse(chunk.done(), chunk.message().content()));
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
}
