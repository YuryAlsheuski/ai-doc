package org.ai.doc.client.factory;

import org.ai.doc.client.domain.Client;
import org.ai.doc.common.model.domain.Model;

public interface ClientFactory {

  <T> Client<T> getClient(Model model);
}
