package org.ai.doc.client.engine.openai.config.api;

import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OpenAiApiConfig {

  @Bean
  OpenAiApi getOpenAiApi() {
    return new OpenAiApi("demo");
  }
}
