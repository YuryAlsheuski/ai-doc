package org.ai.doc.provider.common.config.provider;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.ai.doc.provider.common.domain.Provider;
import org.ai.doc.provider.common.domain.ProviderType;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProviderConfig {

  @Bean
  Map<ProviderType, Provider<EmbeddingClient>> getEmbeddingClientFactories(
      List<Provider<EmbeddingClient>> providers) {

    return providers.stream().collect(Collectors.toMap(Provider::getType, v -> v));
  }

  @Bean
  Map<ProviderType, Provider<ChatClient>> getChatClientFactories(
      List<Provider<ChatClient>> providers) {

    return providers.stream().collect(Collectors.toMap(Provider::getType, v -> v));
  }
}
