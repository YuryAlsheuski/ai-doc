package org.ai.doc.llm.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ai.doc.llm.dto.FileRequestDTO;
import org.ai.doc.llm.dto.RequestDTO;
import org.ai.doc.llm.dto.ResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// todo add swagger

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/llm")
final class LLMController {

  private final ModelMapper modelMapper; // todo delete if do not need

  @PostMapping("/text/embeddings")
  ResponseEntity<ResponseDTO> embeddText(@Valid @RequestBody RequestDTO dto) {
    return ResponseEntity.ok(ResponseDTO.builder().vector(new double[] {0.2222}).build());
  }

  @PostMapping("/text/generations")
  ResponseEntity<ResponseDTO> generateText(@Valid @RequestBody RequestDTO dto) {
    return ResponseEntity.ok(ResponseDTO.builder().output("descrPlaceholder").build());
  }

  @PostMapping("/image/embeddings")
  ResponseEntity<ResponseDTO> embeddImage(@Valid @ModelAttribute FileRequestDTO dto) {
    return ResponseEntity.ok(
        ResponseDTO.builder().vector(new double[] {dto.getFile().getSize()}).build());
  }

  @PostMapping("/image/descriptions")
  ResponseEntity<ResponseDTO> describeImage(@Valid @ModelAttribute FileRequestDTO dto) {
    return ResponseEntity.ok(ResponseDTO.builder().output("descrPlaceholder").build());
  }
}
