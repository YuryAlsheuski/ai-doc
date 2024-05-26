package org.ai.doc.client.domain;

import org.ai.doc.common.domain.LLMItem;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.ModelOptions;

public interface Client<T> extends LLMItem {
  T call(Prompt prompt, ModelOptions modelOptions);
}
