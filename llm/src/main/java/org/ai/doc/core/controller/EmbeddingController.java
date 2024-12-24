package org.ai.doc.core.controller;

// todo add swagger

import static org.ai.doc.model.domain.Action.TEXT_EMBEDDING;
import static org.ai.doc.model.domain.EngineType.OLLAMA;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ai.doc.core.converter.LLMRequestConverter;
import org.ai.doc.core.dto.EmbeddingResponseDTO;
import org.ai.doc.core.dto.ModelOptionsDTO;
import org.ai.doc.core.dto.PromptDTO;
import org.ai.doc.testmodel.service.ModelService;
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

  private final ModelService modelService;
  private final LLMRequestConverter llmRequestConverter;

  @PostMapping("/embeddings")
  ResponseEntity<EmbeddingResponseDTO> embed(@Valid @RequestBody PromptDTO dto) {

    var request = llmRequestConverter.toRequest(dto, OLLAMA, TEXT_EMBEDDING);
    var modelName = ((ModelOptionsDTO) request.getModelOptions()).getModel();
    var model = modelService.getModel(request.getEngine(), request.getAction(), modelName);

    var response = (EmbeddingResponse) model.call(request);
    var vector = response.getResult().getOutput();
    var responseDTO = EmbeddingResponseDTO.builder().vector(vector).size(vector.size()).build();

    return ResponseEntity.ok(responseDTO);
  }
}
