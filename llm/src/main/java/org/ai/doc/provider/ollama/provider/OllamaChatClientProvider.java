package org.ai.doc.provider.ollama.provider;

import org.ai.doc.common.model.domain.Model;
import org.ai.doc.provider.common.converter.ModelOptionConverter;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.model.ModelOptions;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Component;

@Component
final class OllamaChatClientProvider extends OllamaClientProvider<ChatClient> {

  OllamaChatClientProvider(OllamaApi api, ModelOptionConverter<OllamaOptions> converter) {
    super(api, converter);
  }

  @Override
  public ChatClient getClient(Model model, ModelOptions options) {
    var chatOptions = converter.convert(options).withModel(model.getName());
    return new OllamaChatClient(api).withDefaultOptions(chatOptions);
  }
}
