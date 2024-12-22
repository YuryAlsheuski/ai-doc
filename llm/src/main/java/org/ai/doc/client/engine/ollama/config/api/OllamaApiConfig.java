package org.ai.doc.client.engine.ollama.config.api;

import static org.ai.doc.model.domain.EngineType.OLLAMA;

import org.ai.doc.model.factory.EngineFactory;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OllamaApiConfig {

  @Bean
  OllamaApi getOllamaApi(EngineFactory engineFactory) {
    return new OllamaApi(engineFactory.getEngine(OLLAMA).getUrl());
  }
}
