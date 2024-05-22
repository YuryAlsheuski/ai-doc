package org.ai.doc.core.controller;

import static org.ai.doc.provider.common.domain.ProviderType.OLLAMA;

import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ai.doc.core.dto.FileRequestDTO;
import org.ai.doc.core.dto.RequestDTO;
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

  @PostMapping("/text/embeddings")
  ResponseEntity<ResponseDTO> embeddText(@Valid @RequestBody RequestDTO dto) {
    var prompt = new Prompt(dto.getQuery());
    var vector = clientFactory.getEmbeddingClient(OLLAMA, dto).call(prompt);
    return ResponseEntity.ok(ResponseDTO.builder().vector(vector).build());
  }

  @PostMapping("/text/generations")
  ResponseEntity<ResponseDTO> generateText(@Valid @RequestBody RequestDTO dto) {
    return ResponseEntity.ok(
        ResponseDTO.builder().output(testService.generate(dto.getQuery())).build());
  }

  @PostMapping("/image/embeddings")
  ResponseEntity<ResponseDTO> embeddImage(@Valid @ModelAttribute FileRequestDTO dto) {
    return ResponseEntity.ok(ResponseDTO.builder().vector(List.of()).build());
  }

  @PostMapping("/image/descriptions")
  ResponseEntity<ResponseDTO> describeImage(@Valid @ModelAttribute FileRequestDTO dto)
      throws IOException {
    var description = testService.describeImage(dto.getQuery(), dto.getFile().getBytes());
    return ResponseEntity.ok(ResponseDTO.builder().output(description).build());
  }
}
