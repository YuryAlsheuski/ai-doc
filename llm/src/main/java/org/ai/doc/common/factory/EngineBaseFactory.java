package org.ai.doc.common.factory;

import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import lombok.Setter;
import org.ai.doc.common.domain.Engine;
import org.ai.doc.common.domain.EngineType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "llm")
@Setter
public class EngineBaseFactory implements EngineFactory {
  private Map<EngineType, Engine> engineTypeToEngine;

  public void setEngines(List<Engine> engines) {
    engineTypeToEngine = engines.stream().collect(toMap(Engine::getType, engine -> engine));
  }

  public Engine getEngine(EngineType engineType) {
    return engineTypeToEngine.get(engineType);
  }
}
