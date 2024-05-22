package org.ai.doc.provider.openai.config;

import static org.ai.doc.provider.common.domain.ProviderType.OPEN_AI;
import static org.springframework.ai.document.MetadataMode.EMBED;
import static org.springframework.ai.retry.RetryUtils.DEFAULT_RETRY_TEMPLATE;

import org.ai.doc.provider.common.domain.Provider;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiEmbeddingClient;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OpenAiConfig {

  @Bean
  OpenAiApi getApi() {
    return new OpenAiApi("demo");
  }

  @Bean("OpenAiEmbeddingClient")
  Provider<EmbeddingClient> getEmbeddingProvider(OpenAiApi api) {
    return new Provider<>(
        OPEN_AI,
        (options) ->
            new OpenAiEmbeddingClient(
                api, EMBED, (OpenAiEmbeddingOptions) options, DEFAULT_RETRY_TEMPLATE));
  }

  @Bean("OpenAiChatClient")
  Provider<ChatClient> getChatProvider(OpenAiApi api) {
    return new Provider<>(
        OPEN_AI, (options) -> new OpenAiChatClient(api, (OpenAiChatOptions) options));
  }
}
