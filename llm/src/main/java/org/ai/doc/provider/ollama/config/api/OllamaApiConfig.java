package org.ai.doc.provider.ollama.config.api;

import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OllamaApiConfig {

  //@Value("${llm.default.url}")
  private String llmURL="test";

  @Bean
  OllamaApi getOllamaApi() {
    return new OllamaApi(llmURL);
  }
}
