package org.ai.doc.model.factory;

import java.text.MessageFormat;
import java.util.Set;
import lombok.Setter;
import org.ai.doc.model.domain.EngineType;
import org.ai.doc.model.domain.Model;
import org.ai.doc.model.domain.ModelType;
import org.ai.doc.model.exception.ModelNotFoundException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "llm")
@Setter
final class ModelBaseFactory implements ModelFactory {

  private Set<Model> models;

  @Override
  public Model getModel(EngineType engineType, ModelType modelType, String modelName) {
    return models.stream()
        .filter(
            model ->
                model.getEngine() == engineType
                    && model.getType() == modelType
                    && (modelName == null || model.getName().equals(modelName)))
        .findFirst()
        .orElseThrow(
            () -> {
              var message =
                  MessageFormat.format(
                      "Model not found! Model name = {0}, model type = {1}, engine = {2}",
                      modelName, modelType, engineType);
              return new ModelNotFoundException(message);
            });
  }

  @Override
  public Model getModel(EngineType engineType, ModelType modelType) {
    return getModel(engineType, modelType, null);
  }

  @Override
  public Set<Model> getAll() {
    return Set.copyOf(models);
  }
}
