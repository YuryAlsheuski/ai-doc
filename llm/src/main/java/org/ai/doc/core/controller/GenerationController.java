package org.ai.doc.core.controller;

// todo add swagger

import static org.ai.doc.common.engine.domain.EngineType.OLLAMA;
import static org.ai.doc.common.model.domain.ModelType.TEXT_GENERATION;

import jakarta.validation.Valid;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.ai.doc.client.factory.ClientFactory;
import org.ai.doc.core.converter.ModelOptionsDTOConverter;
import org.ai.doc.core.converter.PromptDTOConverter;
import org.ai.doc.core.dto.ChatResponseDTO;
import org.ai.doc.core.dto.PromptDTO;
import org.springframework.ai.chat.model.ChatResponse;
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
  private final PromptDTOConverter promptConverter;
  private final ModelOptionsDTOConverter modelOptionsConverter;

  @PostMapping("/text/generations")
  CorePublisher<ChatResponseDTO> generate(
      @Valid @RequestBody PromptDTO dto, @RequestParam(defaultValue = "false") String stream) {

    var requestMedia = dto.getMedia();
    var modelType = requestMedia == null ? TEXT_GENERATION : requestMedia.getType().getModelType();
    var model = modelOptionsConverter.toModel(dto.getModelOptions(), OLLAMA, modelType);
    var modelOptions = modelOptionsConverter.toModelOptions(dto.getModelOptions(), model.getName());

    var prompt = promptConverter.toPrompt(dto);

    var client = clientFactory.<ChatResponse>getClient(model);

    if (Boolean.parseBoolean(stream)) {
      return client.stream(prompt, modelOptions).map(parse());
    }
    var response = client.call(prompt, modelOptions);
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
}
