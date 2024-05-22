package org.ai.doc.provider.ollama.config;

import static org.ai.doc.provider.common.domain.ProviderType.OLLAMA;

import org.ai.doc.provider.common.domain.Provider;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.ai.ollama.OllamaEmbeddingClient;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OllamaConfig {

  @Value("${llm.default.url}")
  private String llmURL;

  @Bean
  OllamaApi createApi() {
    return new OllamaApi(llmURL);
  }

  @Bean("OllamaEmbeddingClient")
  Provider<EmbeddingClient> getEmbeddingProvider(OllamaApi api) {
    return new Provider<>(
        OLLAMA,
        (options) -> new OllamaEmbeddingClient(api).withDefaultOptions((OllamaOptions) options));
  }

  @Bean("OllamaChatClient")
  Provider<ChatClient> getChatProvider(OllamaApi api) {
    return new Provider<>(
        OLLAMA, (options) -> new OllamaChatClient(api).withDefaultOptions((OllamaOptions) options));
  }
}
