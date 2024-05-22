package org.ai.doc.core.domain;

import org.springframework.ai.chat.prompt.Prompt;

@FunctionalInterface
public interface LLMClient<T> {
  T call(Prompt prompt);
}
