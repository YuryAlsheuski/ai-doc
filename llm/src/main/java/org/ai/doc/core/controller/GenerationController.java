package org.ai.doc.core.controller;

// todo add swagger

import static org.ai.doc.common.engine.domain.EngineType.OLLAMA;
import static org.ai.doc.common.model.domain.ModelType.TEXT_GENERATION;
import static org.apache.commons.lang3.ArrayUtils.toPrimitive;

import jakarta.validation.Valid;
import java.util.List;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.ai.doc.client.factory.ClientFactory;
import org.ai.doc.common.model.domain.Model;
import org.ai.doc.common.model.factory.ModelFactory;
import org.ai.doc.core.dto.ChatResponseDTO;
import org.ai.doc.core.dto.PromptDTO;
import org.springframework.ai.chat.messages.Media;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.CorePublisher;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/llm")
public class GenerationController {
  private final ClientFactory clientFactory;
  private final ModelFactory modelFactory;

  @PostMapping("/text/generations")
  CorePublisher<ChatResponseDTO> generate(
      @Valid @RequestBody PromptDTO dto, @RequestParam(defaultValue = "false") String stream) {

    var model = getModel(dto);
    var prompt = getPrompt(dto);

    var client = clientFactory.<ChatResponse>getClient(model);

    if (Boolean.parseBoolean(stream)) {
      return client.stream(prompt, dto.getModelOptions()).map(parse());
    }
    var response = client.call(prompt, dto.getModelOptions());
    return Mono.just(parse().apply(response));
  }

  private Function<ChatResponse, ChatResponseDTO> parse() {
    return response -> {
      var result = response.getResult();
      var usage = response.getMetadata().getUsage();

      var done = result.getMetadata().getFinishReason() != null;
      var content = result.getOutput().getContent();

      var responsePromptTokens = usage.getPromptTokens();
      var promptTokens = responsePromptTokens == 0 ? null : responsePromptTokens;

      var responseGenerationTokes = usage.getGenerationTokens();
      var replyTokens = responseGenerationTokes == 0 ? null : responseGenerationTokes;

      return ChatResponseDTO.builder()
          .done(done)
          .content(content)
          .promptTokens(promptTokens)
          .replyTokens(replyTokens)
          .build();
    };
  }

  private Prompt getPrompt(PromptDTO dto) {
    var requestMedia = dto.getMedia();
    if (requestMedia != null) {
      var resource = new ByteArrayResource(toPrimitive(requestMedia.getContent()));
      var media = List.of(new Media(MimeTypeUtils.ALL, resource));
      return new Prompt(new UserMessage(dto.getQuery(), media));
    }
    return new Prompt(new UserMessage(dto.getQuery()));
  }

  private Model getModel(PromptDTO dto) {
    var requestMedia = dto.getMedia();
    var modelType = requestMedia == null ? TEXT_GENERATION : requestMedia.getType().getModelType();
    return modelFactory.getModel(OLLAMA, modelType);
  }
}
