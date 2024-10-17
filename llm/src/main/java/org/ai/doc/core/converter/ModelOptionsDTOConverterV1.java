package org.ai.doc.core.converter;

import lombok.RequiredArgsConstructor;
import org.ai.doc.common.engine.domain.EngineType;
import org.ai.doc.common.model.domain.Model;
import org.ai.doc.common.model.domain.ModelType;
import org.ai.doc.common.model.factory.ModelFactory;
import org.ai.doc.core.dto.ModelOptionsDTO;
import org.springframework.ai.model.ModelOptions;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ModelOptionsDTOConverterV1 implements ModelOptionsDTOConverter {

  private final ModelFactory modelFactory;

  @Override
  public Model toModel(ModelOptionsDTO dto, EngineType engine, ModelType modelType) {
    if (dto != null) {
      var modelName = dto.getModel();
      if (modelName != null) {
        return modelFactory.getModel(engine, modelType, modelName);
      }
    }
    return modelFactory.getModel(engine, modelType);
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
