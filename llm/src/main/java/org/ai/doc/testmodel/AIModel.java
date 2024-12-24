package org.ai.doc.testmodel;

import java.util.Set;
import org.ai.doc.common.request.LLMRequest;
import org.ai.doc.model.domain.Action;
import org.ai.doc.model.domain.EngineType;
import org.springframework.ai.model.ModelResponse;
import reactor.core.publisher.Flux;

public interface AIModel<T extends ModelResponse<?>> {

  T call(LLMRequest request);

  Flux<T> stream(LLMRequest request);

  EngineType getEngineType();

  Set<Action> getSupportedActions();
}
