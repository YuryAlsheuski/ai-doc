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
  //todo - reimplement "raw" option because of standard spring does not know about it at all =(
  @NotBlank(message = "{model.name.not.blank}")
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
