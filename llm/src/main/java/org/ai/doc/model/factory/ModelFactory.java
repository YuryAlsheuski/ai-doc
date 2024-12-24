package org.ai.doc.model.factory;

import java.util.Set;
import org.ai.doc.model.domain.Action;
import org.ai.doc.model.domain.EngineType;
import org.ai.doc.model.domain.Model;

public interface ModelFactory {

  Model getModel(EngineType engineType, Action action, String modelName);

  Model getModel(EngineType engineType, Action action);

  Set<Model> getAll();
}
