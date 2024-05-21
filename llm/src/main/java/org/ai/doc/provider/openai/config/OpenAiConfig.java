package org.ai.doc.provider.openai.config;

import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OpenAiConfig {

  @Bean
  OpenAiApi getApi() {
    return new OpenAiApi("demo");
  }
}
