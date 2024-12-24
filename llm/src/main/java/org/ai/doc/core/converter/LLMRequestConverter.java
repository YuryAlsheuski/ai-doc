package org.ai.doc.core.converter;

import org.ai.doc.common.request.LLMRequest;
import org.ai.doc.core.dto.PromptDTO;
import org.ai.doc.common.constant.Action;
import org.ai.doc.common.constant.EngineType;

public interface LLMRequestConverter {

  LLMRequest toRequest(PromptDTO dto, EngineType engineType, Action action);
}
