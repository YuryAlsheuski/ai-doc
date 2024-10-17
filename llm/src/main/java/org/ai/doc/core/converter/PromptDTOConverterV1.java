package org.ai.doc.core.converter;

import static org.apache.commons.lang3.ArrayUtils.toPrimitive;
import static org.springframework.util.MimeTypeUtils.ALL;

import java.util.List;
import org.ai.doc.core.dto.PromptDTO;
import org.springframework.ai.chat.messages.Media;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

@Component
public class PromptDTOConverterV1 implements PromptDTOConverter {

  @Override
  public Prompt toPrompt(PromptDTO dto) {
    var requestMedia = dto.getMedia();
    if (requestMedia != null) {
      var resource = new ByteArrayResource(toPrimitive(requestMedia.getContent()));
      var media = List.of(new Media(ALL, resource));
      return new Prompt(new UserMessage(dto.getQuery(), media));
    }
    return new Prompt(new UserMessage(dto.getQuery()));
  }
}
