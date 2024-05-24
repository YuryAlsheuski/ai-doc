package org.ai.doc.provider.common.config.provider;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.ai.doc.common.engine.domain.EngineType;
import org.ai.doc.provider.common.domain.Provider;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProviderConfig {

  @Bean
  Map<EngineType, Provider<EmbeddingClient>> getEmbeddingClientFactories(
      List<Provider<EmbeddingClient>> providers) {

    return providers.stream().collect(Collectors.toMap(Provider::getEngineType, v -> v));
  }

  @Bean
  Map<EngineType, Provider<ChatClient>> getChatClientFactories(
      List<Provider<ChatClient>> providers) {

    return providers.stream().collect(Collectors.toMap(Provider::getEngineType, v -> v));
  }
}
