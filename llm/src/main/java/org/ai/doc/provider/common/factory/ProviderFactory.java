package org.ai.doc.provider.common.factory;

import org.ai.doc.provider.common.domain.ProviderType;
import org.springframework.ai.model.ModelClient;
import org.springframework.beans.factory.FactoryBean;

public abstract class ProviderFactory<T extends ModelClient<?, ?>> implements FactoryBean<T> {

  public abstract ProviderType getProviderType();

  @Override
  public boolean isSingleton() {
    return false;
  }
}
