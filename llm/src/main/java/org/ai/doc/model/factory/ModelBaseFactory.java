package org.ai.doc.model.factory;

import java.text.MessageFormat;
import java.util.Set;
import lombok.Setter;
import org.ai.doc.model.domain.Action;
import org.ai.doc.model.domain.EngineType;
import org.ai.doc.model.domain.Model;
import org.ai.doc.model.exception.ModelNotFoundException;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties(prefix = "llm")
@Setter
final class ModelBaseFactory implements ModelFactory {

  private Set<Model> models;

  @Override
  public Model getModel(EngineType engineType, Action action, String modelName) {
    return models.stream()
        .filter(
            model ->
                model.getEngine() == engineType
                    && model.getAction() == action
                    && (modelName == null || model.getName().equals(modelName)))
        .findFirst()
        .orElseThrow(
            () -> {
              var message =
                  MessageFormat.format(
                      "Model not found! Model name = {0}, model type = {1}, engine = {2}",
                      modelName, action, engineType);
              return new ModelNotFoundException(message);
            });
  }

  @Override
  public Model getModel(EngineType engineType, Action action) {
    return getModel(engineType, action, null);
  }

  @Override
  public Set<Model> getAll() {
    return Set.copyOf(models);
  }
}
