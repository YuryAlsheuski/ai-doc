package org.ai.doc.model.openai.config;

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
