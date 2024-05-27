package org.ai.doc.core.controller;

// todo add swagger

import static org.ai.doc.common.engine.domain.EngineType.OLLAMA;
import static org.ai.doc.common.model.domain.ModelType.TEXT_GENERATION;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ai.doc.client.factory.ClientFactory;
import org.ai.doc.common.model.factory.ModelFactory;
import org.ai.doc.core.dto.PromptDTO;
import org.ai.doc.core.dto.ResponseDTO;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.*;
import reactor.core.CorePublisher;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/llm")
public class GenerationController {
  private final ClientFactory clientFactory;
  private final ModelFactory modelFactory;

  @PostMapping("/text/generations")
  CorePublisher<?> generate(
      @Valid @RequestBody PromptDTO dto, @RequestParam(defaultValue = "false") String stream) {
    // todo NOT SUPPORT IMAGE DESCRIPTION!!!!! implement Ollama chat client like here
    // https://github.com/spring-projects/spring-ai/blob/main/models/spring-ai-ollama/src/main/java/org/springframework/ai/ollama/OllamaChatModel.java
    var prompt = new Prompt(dto.getQuery());
    var model = modelFactory.getModel(OLLAMA, TEXT_GENERATION);
    var client = clientFactory.<ChatResponse>getClient(model);

    if (Boolean.parseBoolean(stream)) {
      return client.stream(
          prompt,
          dto.getModelOptions()); // todo looks like stupid standard response dto- need to set own
      // here
    }
    var response = client.call(prompt, dto.getModelOptions());
    var responseDTO =
        ResponseDTO.builder().output(response.getResult().getOutput().getContent()).build();
    return Mono.just(responseDTO);
  }
}
