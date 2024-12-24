package org.ai.doc.core.controller;

// todo add swagger

import static org.ai.doc.common.constant.Action.TEXT_GENERATION;
import static org.ai.doc.common.constant.EngineType.OLLAMA;

import jakarta.validation.Valid;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.ai.doc.core.converter.LLMRequestConverter;
import org.ai.doc.core.dto.ChatResponseDTO;
import org.ai.doc.core.dto.ModelOptionsDTO;
import org.ai.doc.core.dto.PromptDTO;
import org.ai.doc.model.service.ModelService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.model.ModelResponse;
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

  private final ModelService modelService;
  private final LLMRequestConverter llmRequestConverter;

  @PostMapping("/text/generations")
  @SuppressWarnings("ReactiveStreamsUnusedPublisher")
  CorePublisher<ChatResponseDTO> generate(
      @Valid @RequestBody PromptDTO dto, @RequestParam(defaultValue = "false") String stream) {

    var requestMedia = dto.getMedia();
    var action = requestMedia == null ? TEXT_GENERATION : requestMedia.getType().getAction();
    var request = llmRequestConverter.toRequest(dto, OLLAMA, action);
    var modelName = ((ModelOptionsDTO) request.getModelOptions()).getModel();
    var model = modelService.getModel(request.getEngine(), request.getAction(), modelName);

    if (Boolean.parseBoolean(stream)) {
      return model.stream(request).map(parse());
    }
    var response = model.call(request);
    return Mono.just(parse().apply(response));
  }

  private Function<ModelResponse<?>, ChatResponseDTO> parse() {
    return response -> {
      var chatResponse = (ChatResponse) response;
      var result = chatResponse.getResult();
      var usage = chatResponse.getMetadata().getUsage();

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
