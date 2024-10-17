package org.ai.doc.core.converter;

import org.ai.doc.common.engine.domain.EngineType;
import org.ai.doc.common.model.domain.Model;
import org.ai.doc.common.model.domain.ModelType;
import org.ai.doc.core.dto.ModelOptionsDTO;
import org.springframework.ai.model.ModelOptions;

public interface ModelOptionsDTOConverter {

  Model toModel(ModelOptionsDTO dto, EngineType engine, ModelType modelType);

  ModelOptions toModelOptions(ModelOptionsDTO dto, String defaultModelName);
}
