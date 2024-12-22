package org.ai.doc.model.factory;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import lombok.Setter;
import org.ai.doc.model.domain.Engine;
import org.ai.doc.model.domain.EngineType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "llm")
@Setter
final class EngineBaseFactory implements EngineFactory {
  private Map<EngineType, Engine> engineTypeToEngine;

  public void setEngines(Set<Engine> engines) {
    engineTypeToEngine = engines.stream().collect(toMap(Engine::getType, engine -> engine));
  }

  public Engine getEngine(EngineType engineType) {
    return Optional.ofNullable(engineTypeToEngine.get(engineType))
        .orElseThrow(
            () ->
                new RuntimeException(
                    "No engine found for type " + engineType)); // todo special exception here
  }
}
