package org.ai.doc.common.model.factory;

import java.util.List;
import lombok.Setter;
import org.ai.doc.common.engine.domain.EngineType;
import org.ai.doc.common.model.domain.Model;
import org.ai.doc.common.model.domain.ModelType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "llm")
@Setter
final class ModelBaseFactory implements ModelFactory {
  private List<Model> models;

  @Override
  public Model getModel(EngineType engineType, ModelType modelType, String modelName) {
    return null;
  }

  @Override
  public Model getModel(EngineType engineType, ModelType modelType) {
    return models.stream()
        .filter(model -> model.getEngine() == engineType && model.getType() == modelType)
        .findFirst()
        .orElseThrow(RuntimeException::new); // todo special error here
  }
}
