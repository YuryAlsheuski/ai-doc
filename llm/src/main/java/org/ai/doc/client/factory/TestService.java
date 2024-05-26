package org.ai.doc.client.factory;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.Media;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.ai.ollama.OllamaEmbeddingClient;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
@RequiredArgsConstructor
public class TestService {

 // @Value("${llm.default.model.text.generating.name}")
  private String langModel;

 // @Value("${llm.default.model.text.embedding.name}")
  private String embedModel;

 // @Value("${llm.default.model.image.description.name}")
  private String imgDescriptionModel;

  private final OllamaApi api;

  public List<Double> embed(String text) {
    var options = new OllamaOptions().withModel(embedModel);//set other options here
    var embClient = new OllamaEmbeddingClient(api).withDefaultOptions(options);
    return embClient.embed(text);
  }

  public String generate(String prompt) {
    var options = new OllamaOptions().withModel(langModel);//set other options here
    var olClient = new OllamaChatClient(api).withDefaultOptions(options);
    return olClient.call(prompt);
  }

  public String describeImage(String prompt, byte[] content) {

    var options = new OllamaOptions().withModel(imgDescriptionModel);//set other options here
    var olClient = new OllamaChatClient(api).withDefaultOptions(options);
    var message = new UserMessage(prompt, List.of(new Media(MimeTypeUtils.IMAGE_PNG,content)));
    Prompt pr = new Prompt(message);
    return olClient.call(pr).getResult().getOutput().getContent();
  }


  public String openAITest(String prompt){
    var openAiApi = new OpenAiApi("sk-proj-4PtTeEDgeJP9Z1m5M0n2T3BlbkFJalJmEvAXmZoNYCPilmqY");
    var client = new OpenAiChatClient(openAiApi);
    return client.call(prompt);
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
