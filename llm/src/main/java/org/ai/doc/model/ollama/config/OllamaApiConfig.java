package org.ai.doc.model.ollama.config;

import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OllamaApiConfig {

  @Value("${llm.engines.ollama.url}")
  private String ollamaUrl;

  @Bean
  OllamaApi getOllamaApi() {
    return new OllamaApi(ollamaUrl);
  }
}
