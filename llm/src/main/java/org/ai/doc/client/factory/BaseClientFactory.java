package org.ai.doc.client.factory;


import java.util.List;
import org.ai.doc.client.domain.Client;
import org.ai.doc.model.domain.Action;
import org.ai.doc.model.domain.EngineType;
import org.ai.doc.model.domain.Model;
import org.springframework.ai.model.ModelResponse;
import org.springframework.stereotype.Component;

@Component
final class BaseClientFactory implements ClientFactory {

 // private final Map<Model, Client<?>> modelToClientMap;

/*  BaseClientFactory(ModelFactory factory, List<Client<?>> clients) {
    modelToClientMap =
        factory.getAll().stream()
            .collect(toMap(k -> k, v -> getClient(v.getEngine(), v.getAction(), clients)));
  }*/

  @Override
  @SuppressWarnings("unchecked")
  public <T extends ModelResponse<?>> Client<T> getClient(Model model) {
   /* var client = modelToClientMap.get(model);
    if (client == null) {
      throw new RuntimeException(); // todo personal here
    }
    return (Client<T>) client;*/
    return null;
  }

  private Client<?> getClient(EngineType engineType, Action action, List<Client<?>> clients) {
    return clients.stream()
        .filter(
            client ->
                client.getEngineType() == engineType
                    && client.getSupportedModelTypes().contains(action))
        .findFirst()
        .orElseThrow(RuntimeException::new); // todo personal exception here
  }
}
