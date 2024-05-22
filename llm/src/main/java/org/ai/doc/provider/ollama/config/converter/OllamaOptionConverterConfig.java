package org.ai.doc.provider.ollama.config.converter;

import lombok.RequiredArgsConstructor;
import org.ai.doc.provider.common.converter.ModelOptionConverter;
import org.modelmapper.ModelMapper;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class OllamaOptionConverterConfig {

  private final ModelMapper modelMapper;

  @Bean
  ModelOptionConverter<OllamaOptions> getOllamaOptionsConverter() {
    return (options) -> modelMapper.map(options, OllamaOptions.class);
  }
}
