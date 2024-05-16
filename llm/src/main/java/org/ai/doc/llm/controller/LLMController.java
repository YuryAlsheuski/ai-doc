package org.ai.doc.llm.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ai.doc.llm.dto.FileRequestDTO;
import org.ai.doc.llm.dto.RequestDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/llm")
final class LLMController {

  @PostMapping(path = "/text/embeddings", consumes = APPLICATION_JSON_VALUE)
  Double[] embeddText(@Valid @RequestBody RequestDTO dto) {
    return new Double[] {0.2222};
  }

  @PostMapping(path = "/image/embeddings", consumes = MULTIPART_FORM_DATA_VALUE)
  Double[] embeddImage(@ModelAttribute FileRequestDTO dto) {
    return new Double[] {0.2222};
  }


  /*
    describeImage
    generateCode
    answerQuestion
  */

}
