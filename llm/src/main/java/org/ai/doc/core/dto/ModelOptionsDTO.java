package org.ai.doc.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.ai.model.ModelOptions;

@Getter
@Setter
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModelOptionsDTO implements ModelOptions {

  private String model;

  @DecimalMin(value = "0.0", message = "{model.option.temperature.min}")
  @DecimalMax(value = "1.0", message = "{model.option.temperature.max}")
  private Double temperature;

  @Min(value = 1, message = "{model.option.topk}")
  private Integer topK;

  @DecimalMin(value = "0.0", message = "{model.option.topp.min}")
  @DecimalMax(value = "1.0", message = "{model.option.topp.max}")
  private Double topP;

  @DecimalMin(value = "0.1", message = "{model.option.penalty.min}")
  private Double repeatPenalty;

  @PositiveOrZero(message = "{model.option.seed}")
  private Integer seed;

  @Positive(message = "{model.option.num.predict}")
  private Integer numPredict;

  @Positive(message = "{model.option.num.ctx}")
  private Integer numCtx;

  @Size(max = 10, message = "{model.option.stop.max.size}")
  private List<String> stop;
}
