package org.ai.doc.provider.common.factory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.ai.doc.provider.common.domain.ProviderType;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProviderFactoryConfig {

  @Bean
  Map<ProviderType, ProviderFactory<EmbeddingClient>> getEmbeddingClientFactories(
      List<ProviderFactory<EmbeddingClient>> factories) {

    return factories.stream().collect(Collectors.toMap(ProviderFactory::getProviderType, v -> v));
  }

  @Bean
  Map<ProviderType, ProviderFactory<ChatClient>> getChatClientFactories(
      List<ProviderFactory<ChatClient>> factories) {

    return factories.stream().collect(Collectors.toMap(ProviderFactory::getProviderType, v -> v));
  }
}
