package org.ai.doc.llm.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

  @Value("${llm.default.model.text.generating.name}")
  private String langModel;

  @Value("${llm.default.model.text.embedding.name}")
  private String embedModel;

  @Value("${llm.default.model.image.description.name}")
  private String imgDescriptionModel;

  private final OllamaApi api;

  public List<Double> embed(String text) {
    var request = new OllamaApi.EmbeddingRequest(embedModel, text);
    var response = api.embeddings(request);
    return response.embedding();
  }

  public String generate(String prompt) {
    var request =
        OllamaApi.GenerateRequest.builder(prompt).withStream(false).withModel(langModel).build();
    var response = api.generate(request);
    return response.response();
  }

  public String describeImage(String prompt, byte[] content) {
    // var message = new UserMessage(prompt, List.of(new Media(MimeTypeUtils.IMAGE_PNG,content)));
    var messages =
        List.of(
            OllamaApi.Message.builder(OllamaApi.Message.Role.USER)
                .withContent(prompt)
                .withImages(List.of(content))
                .build());

    var request =
        OllamaApi.ChatRequest.builder(imgDescriptionModel)
            .withStream(false)
            .withMessages(messages)
            .build();

    return api.chat(request).message().content();
  }

  public String test() {

    // Sync request
    var request =
        OllamaApi.ChatRequest.builder(langModel)
            .withStream(false) // not streaming
            .withMessages(
                List.of(
                    OllamaApi.Message.builder(OllamaApi.Message.Role.SYSTEM)
                        .withContent("You are geography teacher. You are talking to a student.")
                        .build(),
                    OllamaApi.Message.builder(OllamaApi.Message.Role.USER)
                        .withContent(
                            "What is the capital of Bulgaria and what is the size? "
                                + "What it the national anthem?")
                        .build()))
            .withOptions(OllamaOptions.create().withTemperature(0.9f))
            .build();

    OllamaApi.ChatResponse response = api.chat(request);

    return response.message().content();

    /*  // Streaming request
    var request2 =
        OllamaApi.ChatRequest.builder(langModel)
            .withStream(true) // streaming
            .withMessages(
                List.of(
                    OllamaApi.Message.builder(OllamaApi.Message.Role.USER)
                        .withContent(
                            "What is the capital of Bulgaria and what is the size? "
                                + "What it the national anthem?")
                        .build()))
            .withOptions(OllamaOptions.create().withTemperature(0.9f).toMap())
            .build();

    Flux<OllamaApi.ChatResponse> streamingResponse = ollamaApi.streamingChat(request2);*/
  }
}
