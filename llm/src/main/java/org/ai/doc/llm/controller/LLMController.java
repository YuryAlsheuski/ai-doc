package org.ai.doc.llm.controller;

import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ai.doc.llm.dto.FileRequestDTO;
import org.ai.doc.llm.dto.RequestDTO;
import org.ai.doc.llm.dto.ResponseDTO;
import org.ai.doc.llm.service.TestService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// todo add swagger

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/llm")
final class LLMController {

  private final ModelMapper modelMapper; // todo delete if do not need
  private final TestService testService;

  @PostMapping("/text/embeddings")
  ResponseEntity<ResponseDTO> embeddText(@Valid @RequestBody RequestDTO dto) {
    var vector = testService.embed(dto.getQuery());
    return ResponseEntity.ok(ResponseDTO.builder().vector(vector).build());
  }

  @PostMapping("/text/generations")
  ResponseEntity<ResponseDTO> generateText(@Valid @RequestBody RequestDTO dto) {
    return ResponseEntity.ok(ResponseDTO.builder().output(testService.generate(dto.getQuery())).build());
  }

  @PostMapping("/image/embeddings")
  ResponseEntity<ResponseDTO> embeddImage(@Valid @ModelAttribute FileRequestDTO dto) {
    return ResponseEntity.ok(ResponseDTO.builder().vector(List.of()).build());
  }

  @PostMapping("/image/descriptions")
  ResponseEntity<ResponseDTO> describeImage(@Valid @ModelAttribute FileRequestDTO dto) throws IOException {
    var description = testService.describeImage(dto.getQuery(),dto.getFile().getBytes());
    return ResponseEntity.ok(ResponseDTO.builder().output(description).build());
  }
}
