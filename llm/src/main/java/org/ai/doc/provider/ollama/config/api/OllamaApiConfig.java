package org.ai.doc.provider.ollama.config.api;

import static org.ai.doc.common.engine.domain.EngineType.OLLAMA;

import org.ai.doc.common.engine.factory.EngineFactory;
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
