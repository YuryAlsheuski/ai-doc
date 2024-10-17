package org.ai.doc.core.converter;

import org.ai.doc.core.dto.PromptDTO;
import org.springframework.ai.chat.prompt.Prompt;

public interface PromptDTOConverter {

  Prompt toPrompt(PromptDTO dto);
}
