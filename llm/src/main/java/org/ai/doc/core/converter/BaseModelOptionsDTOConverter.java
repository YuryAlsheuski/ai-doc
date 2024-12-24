package org.ai.doc.core.converter;

import lombok.RequiredArgsConstructor;
import org.ai.doc.core.dto.ModelOptionsDTO;
import org.ai.doc.model.domain.EngineType;
import org.ai.doc.model.domain.Model;
import org.ai.doc.model.domain.Action;
import org.ai.doc.model.factory.ModelFactory;
import org.springframework.ai.model.ModelOptions;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BaseModelOptionsDTOConverter implements ModelOptionsDTOConverter {

  private final ModelFactory modelFactory;

  @Override
  public Model toModel(ModelOptionsDTO dto, EngineType engine, Action action) {
    if (dto != null) {
      var modelName = dto.getModel();
      if (modelName != null) {
        return modelFactory.getModel(engine, action, modelName);
      }
    }
    return modelFactory.getModel(engine, action);
  }

  @Override
  public ModelOptions toModelOptions(ModelOptionsDTO dto, String defaultModelName) {
    if (dto == null) {
      return new ModelOptions() {
        public String getModel() {
          return defaultModelName;
        }
      };
    }
    var modelName = dto.getModel();
    if (modelName == null) {
      dto.setModel(defaultModelName);
    }
    return dto;
  }
}
