package org.ai.doc.client.factory;

import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import org.ai.doc.client.domain.Client;
import org.ai.doc.common.domain.LLMItem;
import org.ai.doc.common.engine.domain.EngineType;
import org.ai.doc.common.model.domain.Model;
import org.ai.doc.common.model.domain.ModelType;
import org.ai.doc.common.model.factory.ModelFactory;
import org.springframework.stereotype.Component;

@Component
final class BaseClientFactory implements ClientFactory {

  private final Map<Model, Client<?>> modelToClientMap;

  BaseClientFactory(ModelFactory factory, List<LLMItem> clients) {
    modelToClientMap =
        factory.getAll().stream()
            .collect(toMap(k -> k, v -> getClient(v.getEngineType(), v.getModelType(), clients)));
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> Client<T> getClient(Model model) {
    var client = modelToClientMap.get(model);
    if (client == null) {
      throw new RuntimeException(); // todo personal here
    }
    return (Client<T>) client;
  }

  private Client<?> getClient(EngineType engineType, ModelType modelType, List<LLMItem> clients) {
    return (Client<?>)
        clients.stream()
            .filter(
                client ->
                    client.getEngineType() == engineType && client.getModelType() == modelType)
            .findFirst()
            .orElseThrow(RuntimeException::new); // todo personal exception here
  }
}
