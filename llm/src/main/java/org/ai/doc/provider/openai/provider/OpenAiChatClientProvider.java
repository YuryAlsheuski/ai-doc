package org.ai.doc.provider.openai.provider;

import org.ai.doc.common.domain.Model;
import org.ai.doc.provider.common.converter.ModelOptionConverter;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.model.ModelOptions;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Component;

@Component
final class OpenAiChatClientProvider extends OpenAiClientProvider<ChatClient> {

  private final ModelOptionConverter<OpenAiChatOptions> converter;

  OpenAiChatClientProvider(OpenAiApi api, ModelOptionConverter<OpenAiChatOptions> converter) {
    super(api);
    this.converter = converter;
  }

  @Override
  public ChatClient getClient(Model model, ModelOptions options) {
    var chatOptions = converter.convert(options);
    chatOptions.setModel(model.getName());
    return new OpenAiChatClient(api, chatOptions);
  }
}
