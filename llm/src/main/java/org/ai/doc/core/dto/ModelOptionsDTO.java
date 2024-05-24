package org.ai.doc.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.ai.model.ModelOptions;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModelOptionsDTO implements ModelOptions {

  @NotBlank(message = "{query.not.blank}")
  private String query;

  private String model;
  private Double temperature;
  private Integer topK;
  private Double topP;
  private Double repeatPenalty;
  private Integer seed;
  private Integer numPredict;
  private Integer numCtx;
  private List<String> stop;
}
