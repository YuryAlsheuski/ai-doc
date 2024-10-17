package org.ai.doc.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.ai.doc.core.domain.ContentType;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaDTO { // todo add validation in the future

  @NotNull(message = "{media.type.not.null}")
  private ContentType type;

  @NotNull(message = "{content.not.empty}")
  @NotEmpty(message = "{content.not.empty}")
  private Byte[] content;
}
