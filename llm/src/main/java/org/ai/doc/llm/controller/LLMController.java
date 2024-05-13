package org.ai.doc.llm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/llm")
final class LLMController {

   @PostMapping("/text/embeddings")
   String test(){
       return "ttt";
   }
}
