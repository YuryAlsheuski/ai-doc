package org.ai.doc.common.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Model {
  private ModelType type;
  private String name;
}
