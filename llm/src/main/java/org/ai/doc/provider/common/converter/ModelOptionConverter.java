package org.ai.doc.provider.common.converter;

import org.springframework.ai.model.ModelOptions;

@FunctionalInterface
public interface ModelOptionConverter<T extends ModelOptions> {
  T convert(Object options);
}
