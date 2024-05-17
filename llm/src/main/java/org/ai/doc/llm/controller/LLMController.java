package org.ai.doc.llm.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ai.doc.llm.dto.FileRequestDTO;
import org.ai.doc.llm.dto.RequestDTO;
import org.ai.doc.llm.dto.ResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/llm")
final class LLMController {

  private final ModelMapper modelMapper;//todo delete if do not need

  @PostMapping(path = "/text/embeddings", consumes = APPLICATION_JSON_VALUE)
  ResponseEntity<ResponseDTO> embeddText(@Valid @RequestBody RequestDTO dto) {
    return ResponseEntity.ok(ResponseDTO.builder().vector(new double[] {0.2222}).build());
  }

  @PostMapping(path = "/text/generations", consumes = APPLICATION_JSON_VALUE)
  ResponseEntity<ResponseDTO> generateText(@Valid @RequestBody RequestDTO dto) {
    return ResponseEntity.ok(ResponseDTO.builder().output("descrPlaceholder").build());
  }


  @PostMapping(path = "/image/embeddings", consumes = MULTIPART_FORM_DATA_VALUE)
  ResponseEntity<ResponseDTO> embeddImage(@Valid @ModelAttribute FileRequestDTO dto) {
    return ResponseEntity.ok(ResponseDTO.builder().vector(new double[] {0.2222}).build());
  }

  @PostMapping(path = "/image/descriptions", consumes = MULTIPART_FORM_DATA_VALUE)
  ResponseEntity<ResponseDTO> describeImage(@Valid @ModelAttribute FileRequestDTO dto) {
    return ResponseEntity.ok(ResponseDTO.builder().output("descrPlaceholder").build());
  }

}
