package org.ai.doc.testmodel.service;

import static java.text.MessageFormat.format;
import static java.util.stream.Collectors.toMap;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Setter;
import org.ai.doc.model.domain.Action;
import org.ai.doc.model.domain.EngineType;
import org.ai.doc.model.domain.Model;
import org.ai.doc.model.exception.ModelNotFoundException;
import org.ai.doc.testmodel.AIModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "llm")
public class CustomModelService implements ModelService {

  private Map<Model, AIModel<?>> modelDescriptionToAiModel;
  @Autowired private List<AIModel<?>> customModels;
  @Setter private Set<Model> models;

  @Override
  public AIModel<?> getModel(EngineType engineType, Action action, String modelName) {
    var modelDescription = new Model(engineType, action, modelName);
    var aiModel = modelDescriptionToAiModel.get(modelDescription);
    if (aiModel == null) {
      throw getModelNotFoundException(engineType, action, modelName);
    }
    return aiModel;
  }

  @Override
  public String getDefaultName(EngineType engineType, Action action) {
    var model =
        models.stream()
            .filter(m -> m.getEngine() == engineType && m.getAction() == action)
            .findFirst()
            .orElseThrow(() -> getModelNotFoundException(engineType, action, null));
    return model.getName();
  }

  @PostConstruct
  void init() {
    modelDescriptionToAiModel =
        models.stream().collect(toMap(k -> k, v -> getModel(v.getEngine(), v.getAction())));
  }

  private AIModel<?> getModel(EngineType engineType, Action action) {
    return customModels.stream()
        .filter(
            model ->
                model.getEngineType() == engineType && model.getSupportedActions().contains(action))
        .findFirst()
        .orElseThrow(() -> getModelNotFoundException(engineType, action, null));
  }

  private ModelNotFoundException getModelNotFoundException(
      EngineType engineType, Action action, String modelName) {

    var modelNamePart =
        StringUtils.isBlank(modelName) ? " " : format(" model name = {0}, ", modelName);
    var message =
        format(
            "Model not found!{0}model action type = {1}, engine = {2}",
            modelNamePart, action, engineType);
    return new ModelNotFoundException(message);
  }
}
