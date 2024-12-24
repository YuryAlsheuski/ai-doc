package org.ai.doc.client.domain;

import java.util.Set;
import org.ai.doc.model.domain.EngineType;
import org.ai.doc.model.domain.Action;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.ModelOptions;
import org.springframework.ai.model.ModelResponse;
import reactor.core.publisher.Flux;

public interface Client<T extends ModelResponse<?>> {

  T call(Prompt prompt, ModelOptions modelOptions);

  Flux<T> stream(Prompt prompt, ModelOptions modelOptions);

  EngineType getEngineType();

  Set<Action> getSupportedModelTypes();
}
