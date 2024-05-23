package org.ai.doc.common.domain;

import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "llm")
@Setter
public class EngineConfig {
  private Map<EngineType, Engine> engineTypeToEngine;

  public void setEngines(List<Engine> engines) {
    engineTypeToEngine = engines.stream().collect(toMap(Engine::getType, engine -> engine));
  }

  public Engine getEngine(EngineType engineType) {
    return engineTypeToEngine.get(engineType);
  }

  @Setter
  @Getter
  public static class Engine {
    private EngineType type;
    private String url;
    private Map<ModelType, Model> modelTypeToModel;

    public void setModels(List<Model> models) {
      modelTypeToModel = models.stream().collect(toMap(Model::getType, model -> model));
    }

    public Model getModel(ModelType modelType) {
      return modelTypeToModel.get(modelType);
    }

    @Setter
    @Getter
    public static class Model {
      private ModelType type;
      private String name;
    }
  }
}
