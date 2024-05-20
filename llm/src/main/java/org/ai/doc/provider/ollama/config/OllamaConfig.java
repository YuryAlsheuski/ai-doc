package org.ai.doc.provider.ollama.config;

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
