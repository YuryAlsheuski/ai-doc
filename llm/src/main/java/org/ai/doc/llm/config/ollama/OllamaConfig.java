package org.ai.doc.llm.config.ollama;

import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OllamaConfig {

  @Value("${llm.default.url}")
  private String llmURL;

  @Bean
  OllamaApi createApi() {
    return new OllamaApi(llmURL);
  }
}
