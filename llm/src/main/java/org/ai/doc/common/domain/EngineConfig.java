package org.ai.doc.common.domain;

import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "llm")
@Data
public class EngineConfig {
  private Map<EngineType, Engine> engineTypeToEngine;

  public void setEngines(List<Engine> engines) {
    engineTypeToEngine = engines.stream().collect(toMap(Engine::getType, engine -> engine));
  }

  public Engine getEngine(EngineType engineType) {
    return engineTypeToEngine.get(engineType);
  }

  @Data
  public static class Engine {
    private EngineType type;
    private String url;
    private List<Model> models;

    @Data
    public static class Model {
      private String type;
      private TextModel text;
      private ImageModel image;

      @Data
      public static class TextModel {
        private EmbeddingModel embedding;
        private GenerationModel generation;

        @Data
        public static class EmbeddingModel {
          private String name;
        }

        @Data
        public static class GenerationModel {
          private String name;
        }
      }

      @Data
      public static class ImageModel {
        private DescriptionModel description;

        @Data
        public static class DescriptionModel {
          private String name;
        }
      }
    }
  }
}
