package org.ai.doc.llm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestDTO {
  private String prompt;
  private Double temperature;
  private Integer topK;
  private Double topP;
  private Double repeatPenalty;
  private Integer seed;
  private Integer numPredict;
  private Integer numCtx;
  private List<String> stop;
}
