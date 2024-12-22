package org.ai.doc.client.factory;

import org.ai.doc.client.domain.Client;
import org.ai.doc.model.domain.Model;
import org.springframework.ai.model.ModelResponse;

public interface ClientFactory {

  <T extends ModelResponse<?>> Client<T> getClient(Model model);
}
