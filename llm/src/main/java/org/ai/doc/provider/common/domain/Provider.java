package org.ai.doc.provider.common.domain;

import java.util.function.Function;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.model.ModelClient;
import org.springframework.ai.model.ModelOptions;

@Getter
@RequiredArgsConstructor
public class Provider<T extends ModelClient<?, ?>> {

  private final ProviderType type;
  private final Function<ModelOptions, T> clientCreator;

  public T getClient(ModelOptions options) {
    return clientCreator.apply(options);
  }
}
