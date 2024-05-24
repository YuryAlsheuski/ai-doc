package org.ai.doc.core.factory;

import java.util.List;
import org.ai.doc.common.domain.Model;
import org.ai.doc.core.domain.LLMClient;
import org.springframework.ai.model.ModelOptions;

public interface ClientFactory {
  LLMClient<String> getChatClient(Model model, ModelOptions modelOptions);

  LLMClient<List<Double>> getEmbeddingClient(Model model, ModelOptions modelOptions);
}
