package org.ai.doc.core.controller;

import static org.ai.doc.common.engine.domain.EngineType.OLLAMA;
import static org.ai.doc.common.model.domain.ModelType.TEXT_EMBEDDING;
import static org.ai.doc.common.model.domain.ModelType.TEXT_GENERATION;

import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ai.doc.client.factory.ClientFactory;
import org.ai.doc.client.factory.TestService;
import org.ai.doc.common.model.factory.ModelFactory;
import org.ai.doc.core.dto.FileModelOptionsDTO;
import org.ai.doc.core.dto.ModelOptionsDTO;
import org.ai.doc.core.dto.ResponseDTO;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

// todo add swagger

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/llm")
final class LLMController {

  private final TestService testService;
  private final ClientFactory clientFactory;
  private final ModelFactory modelFactory;

  @PostMapping("/text/embeddings")
  ResponseEntity<ResponseDTO> embeddText(@Valid @RequestBody ModelOptionsDTO modelOptions) {
    var prompt = new Prompt(modelOptions.getQuery());
    var model = modelFactory.getModel(OLLAMA, TEXT_EMBEDDING);
    var response = clientFactory.<EmbeddingResponse>getClient(model).call(prompt, modelOptions);
    var vector = response.getResult().getOutput();
    return ResponseEntity.ok(ResponseDTO.builder().vector(vector).build());
  }

  @PostMapping("/text/generations")
  Object generateText(@Valid @RequestBody ModelOptionsDTO modelOptions) {

    var prompt = new Prompt(modelOptions.getQuery());
    var model = modelFactory.getModel(OLLAMA, TEXT_GENERATION);
    var client = clientFactory.<ChatResponse>getClient(model);

    var response = client.call(prompt, modelOptions);
    return ResponseEntity.ok(
        ResponseDTO.builder().output(response.getResult().getOutput().getContent()).build());
  }

  @PostMapping("/stream/text/generations")
  Flux<ChatResponse> streamTextGeneration(@Valid @RequestBody ModelOptionsDTO modelOptions) {

    var prompt = new Prompt(modelOptions.getQuery());
    var model = modelFactory.getModel(OLLAMA, TEXT_GENERATION);
    var client = clientFactory.<ChatResponse>getClient(model);

    return client.stream(prompt, modelOptions);
  }

  @PostMapping("/image/embeddings")
  ResponseEntity<ResponseDTO> embeddImage(@Valid @ModelAttribute FileModelOptionsDTO modelOptions) {
    return ResponseEntity.ok(ResponseDTO.builder().vector(List.of()).build());
  }

  @PostMapping("/image/descriptions")
  ResponseEntity<ResponseDTO> describeImage(@Valid @ModelAttribute FileModelOptionsDTO modelOptions)
      throws IOException {
    var description =
        testService.describeImage(modelOptions.getQuery(), modelOptions.getFile().getBytes());
    return ResponseEntity.ok(ResponseDTO.builder().output(description).build());
  }
}
