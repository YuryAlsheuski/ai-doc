package org.ai.doc.core.controller;

// todo add swagger

import static org.ai.doc.common.engine.domain.EngineType.OLLAMA;
import static org.ai.doc.common.model.domain.ModelType.TEXT_EMBEDDING;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ai.doc.client.factory.ClientFactory;
import org.ai.doc.common.model.factory.ModelFactory;
import org.ai.doc.core.dto.EmbeddingResponseDTO;
import org.ai.doc.core.dto.PromptDTO;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/llm")
public class EmbeddingController {
  private final ClientFactory clientFactory;
  private final ModelFactory modelFactory;

  @PostMapping("/embeddings")
  ResponseEntity<EmbeddingResponseDTO> embedd(@Valid @RequestBody PromptDTO dto) {
    var prompt = new Prompt(dto.getQuery());
    var model = modelFactory.getModel(OLLAMA, TEXT_EMBEDDING);
    var response =
        clientFactory.<EmbeddingResponse>getClient(model).call(prompt, dto.getModelOptions());
    var vector = response.getResult().getOutput();
    var responseDTO = EmbeddingResponseDTO.builder().vector(vector).size(vector.size()).build();
    return ResponseEntity.ok(responseDTO);
  }
}
