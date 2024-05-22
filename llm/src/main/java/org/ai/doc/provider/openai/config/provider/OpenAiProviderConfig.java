package org.ai.doc.provider.openai.config.provider;

import static org.ai.doc.common.domain.EngineType.OPEN_AI;
import static org.springframework.ai.document.MetadataMode.EMBED;
import static org.springframework.ai.retry.RetryUtils.DEFAULT_RETRY_TEMPLATE;

import org.ai.doc.provider.common.converter.ModelOptionConverter;
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
class OpenAiProviderConfig {
  @Bean
  Provider<EmbeddingClient> getOpenAiEmbeddingProvider(
      OpenAiApi api, ModelOptionConverter<OpenAiEmbeddingOptions> converter) {
    return new Provider<>(
        OPEN_AI,
        (options) ->
            new OpenAiEmbeddingClient(
                api, EMBED, converter.convert(options), DEFAULT_RETRY_TEMPLATE));
  }

  @Bean
  Provider<ChatClient> getOpenAiProvider(
      OpenAiApi api, ModelOptionConverter<OpenAiChatOptions> converter) {
    return new Provider<>(
        OPEN_AI, (options) -> new OpenAiChatClient(api, converter.convert(options)));
  }
}
