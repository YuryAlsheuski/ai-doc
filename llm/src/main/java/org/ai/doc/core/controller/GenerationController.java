package org.ai.doc.core.controller;

// todo add swagger

import static org.ai.doc.common.engine.domain.EngineType.OLLAMA;
import static org.ai.doc.common.model.domain.ModelType.IMAGE_DESCRIPTION;
import static org.ai.doc.common.model.domain.ModelType.TEXT_GENERATION;
import static org.apache.commons.lang3.ArrayUtils.toPrimitive;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ai.doc.client.factory.ClientFactory;
import org.ai.doc.common.model.domain.Model;
import org.ai.doc.common.model.factory.ModelFactory;
import org.ai.doc.core.dto.PromptDTO;
import org.ai.doc.core.dto.ResponseDTO;
import org.springframework.ai.chat.messages.Media;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.CorePublisher;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/llm")
public class GenerationController {
  private final ClientFactory clientFactory;
  private final ModelFactory modelFactory;

  @PostMapping("/text/generations")
  CorePublisher<?> generate(
      @Valid @RequestBody PromptDTO dto, @RequestParam(defaultValue = "false") String stream) {

    var model = getModel(dto);
    var prompt = getPrompt(dto);

    var client = clientFactory.<ChatResponse>getClient(model);

    if (Boolean.parseBoolean(stream)) {
      return client.stream(
          prompt,
          dto.getModelOptions()); // todo looks like stupid standard response dto- need to set own
      // here
    }
    var response = client.call(prompt, dto.getModelOptions());
    var responseDTO =
        ResponseDTO.builder().output(response.getResult().getOutput().getContent()).build();
    return Mono.just(responseDTO);
  }

  private Prompt getPrompt(PromptDTO dto) {
    var content = dto.getContent();
    if (content != null && content.length > 0) {
      var resource = new ByteArrayResource(toPrimitive(content));
      var media = List.of(new Media(MimeTypeUtils.IMAGE_JPEG, resource));
      return new Prompt(new UserMessage(dto.getQuery(), media));
    }
    return new Prompt(new UserMessage(dto.getQuery()));
  }

  private Model getModel(PromptDTO dto) {
    var content = dto.getContent();
    var modelType = content != null && content.length > 0 ? IMAGE_DESCRIPTION : TEXT_GENERATION;
    return modelFactory.getModel(OLLAMA, modelType);
  }
}
