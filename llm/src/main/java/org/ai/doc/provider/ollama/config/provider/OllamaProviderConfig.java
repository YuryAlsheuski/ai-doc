package org.ai.doc.provider.ollama.config.provider;

import static org.ai.doc.provider.common.domain.ProviderType.OLLAMA;

import lombok.RequiredArgsConstructor;
import org.ai.doc.provider.common.converter.ModelOptionConverter;
import org.ai.doc.provider.common.domain.Provider;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.ai.ollama.OllamaEmbeddingClient;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class OllamaProviderConfig {
  private final ModelOptionConverter<OllamaOptions> converter;

  @Bean
  Provider<EmbeddingClient> getOllamaEmbeddingProvider(OllamaApi api) {
    return new Provider<>(
        OLLAMA,
        (options) -> new OllamaEmbeddingClient(api).withDefaultOptions(converter.convert(options)));
  }

  @Bean
  Provider<ChatClient> getOllamaChatProvider(OllamaApi api) {
    return new Provider<>(
        OLLAMA,
        (options) -> new OllamaChatClient(api).withDefaultOptions(converter.convert(options)));
  }
}
