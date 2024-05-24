package org.ai.doc.core.controller;

import static org.ai.doc.common.domain.EngineType.OLLAMA;
import static org.ai.doc.common.domain.ModelType.TEXT_EMBEDDING;

import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ai.doc.common.factory.model.ModelFactory;
import org.ai.doc.core.dto.FileModelOptionsDTO;
import org.ai.doc.core.dto.ModelOptionsDTO;
import org.ai.doc.core.dto.ResponseDTO;
import org.ai.doc.core.factory.ClientFactory;
import org.ai.doc.core.factory.TestService;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    var vector = clientFactory.getEmbeddingClient(model, modelOptions).call(prompt);
    return ResponseEntity.ok(ResponseDTO.builder().vector(vector).build());
  }

  @PostMapping("/text/generations")
  ResponseEntity<ResponseDTO> generateText(@Valid @RequestBody ModelOptionsDTO modelOptions) {
    return ResponseEntity.ok(
        ResponseDTO.builder().output(testService.generate(modelOptions.getQuery())).build());
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
