package org.ai.doc.llm.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LLMConfig {
  @Bean
  ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
