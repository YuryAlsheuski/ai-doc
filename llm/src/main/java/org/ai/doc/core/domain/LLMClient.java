package org.ai.doc.core.domain;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.ModelOptions;

@FunctionalInterface
public interface LLMClient<T> {
  T call(Prompt prompt, ModelOptions options);
}
