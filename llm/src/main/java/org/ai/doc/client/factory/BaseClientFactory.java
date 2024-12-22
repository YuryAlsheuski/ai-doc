package org.ai.doc.client.factory;

import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import org.ai.doc.client.domain.Client;
import org.ai.doc.model.domain.EngineType;
import org.ai.doc.model.domain.Model;
import org.ai.doc.model.domain.ModelType;
import org.ai.doc.model.factory.ModelFactory;
import org.springframework.ai.model.ModelResponse;
import org.springframework.stereotype.Component;

@Component
final class BaseClientFactory implements ClientFactory {

  private final Map<Model, Client<?>> modelToClientMap;

  BaseClientFactory(ModelFactory factory, List<Client<?>> clients) {
    modelToClientMap =
        factory.getAll().stream()
            .collect(toMap(k -> k, v -> getClient(v.getEngine(), v.getType(), clients)));
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T extends ModelResponse<?>> Client<T> getClient(Model model) {
    var client = modelToClientMap.get(model);
    if (client == null) {
      throw new RuntimeException(); // todo personal here
    }
    return (Client<T>) client;
  }

  private Client<?> getClient(EngineType engineType, ModelType modelType, List<Client<?>> clients) {
    return clients.stream()
        .filter(
            client ->
                client.getEngineType() == engineType
                    && client.getSupportedModelTypes().contains(modelType))
        .findFirst()
        .orElseThrow(RuntimeException::new); // todo personal exception here
  }
}
