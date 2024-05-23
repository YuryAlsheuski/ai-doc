package org.ai.doc.common.domain;

import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Engine {
  private EngineType type;
  private String url;
  private Map<ModelType, Model> modelTypeToModel;

  public void setModels(List<Model> models) {
    modelTypeToModel = models.stream().collect(toMap(Model::getType, model -> model));
  }

  public Model getModel(ModelType modelType) {
    return modelTypeToModel.get(modelType);
  }
}
