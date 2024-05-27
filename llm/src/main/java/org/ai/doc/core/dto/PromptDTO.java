package org.ai.doc.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PromptDTO {
  @NotBlank(message = "{query.not.blank}")
  private String query;

  private Byte[] content; // todo add validation in the future

  @NotNull(message = "{model.options.not.null}")
  @Valid
  private ModelOptionsDTO modelOptions;
}
