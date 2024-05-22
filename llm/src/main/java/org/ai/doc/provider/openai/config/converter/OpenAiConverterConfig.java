package org.ai.doc.provider.openai.config.converter;

import lombok.RequiredArgsConstructor;
import org.ai.doc.provider.common.converter.ModelOptionConverter;
import org.modelmapper.ModelMapper;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class OpenAiConverterConfig {
  private final ModelMapper modelMapper;

  @Bean
  ModelOptionConverter<OpenAiEmbeddingOptions> getOpenAiEmbeddingOptionsConverter() {
    return (options) -> modelMapper.map(options, OpenAiEmbeddingOptions.class);
  }

  @Bean
  ModelOptionConverter<OpenAiChatOptions> getOpenAiChatOptionsConverter() {
    return (options) -> modelMapper.map(options, OpenAiChatOptions.class);
  }
}
