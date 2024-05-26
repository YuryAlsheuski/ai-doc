package org.ai.doc.client.domain;

import org.ai.doc.common.domain.LLMEntity;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.ModelOptions;

public interface Client<T> extends LLMEntity {
  T call(Prompt prompt, ModelOptions modelOptions);
}
