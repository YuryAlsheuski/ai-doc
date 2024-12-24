package org.ai.doc.core.converter;

import static org.apache.commons.lang3.ArrayUtils.toPrimitive;
import static org.springframework.util.MimeTypeUtils.ALL;

import java.util.List;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import org.ai.doc.common.constant.Action;
import org.ai.doc.common.constant.EngineType;
import org.ai.doc.common.request.LLMRequest;
import org.ai.doc.core.dto.ModelOptionsDTO;
import org.ai.doc.core.dto.PromptDTO;
import org.ai.doc.model.service.ModelService;
import org.springframework.ai.chat.messages.Media;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.model.ModelOptions;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LLMRequestConverterBase implements LLMRequestConverter {

  private final ModelService modelService;

  @Override
  public LLMRequest toRequest(PromptDTO dto, EngineType engineType, Action action) {
    var options =
        toModelOptions(
            dto.getModelOptions(), () -> modelService.getDefaultName(engineType, action));
    return new LLMRequest(getUserMessage(dto), options, engineType, action);
  }

  private UserMessage getUserMessage(PromptDTO dto) {
    var requestMedia = dto.getMedia();
    if (requestMedia == null) {
      return new UserMessage(dto.getQuery());
    }
    var resource = new ByteArrayResource(toPrimitive(requestMedia.getContent()));
    var media = List.of(new Media(ALL, resource));
    return new UserMessage(dto.getQuery(), media);
  }

  private ModelOptions toModelOptions(ModelOptionsDTO dto, Supplier<String> defModelName) {
    if (dto == null) {
      return new ModelOptionsDTO().setModel(defModelName.get());
    }
    var modelName = dto.getModel();
    if (modelName == null) {
      dto.setModel(defModelName.get());
    }
    return dto;
  }
}
