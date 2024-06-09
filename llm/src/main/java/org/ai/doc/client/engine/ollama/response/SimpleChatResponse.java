package org.ai.doc.client.engine.ollama.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import lombok.Getter;
import org.springframework.ai.chat.metadata.ChatResponseMetadata;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;

@Getter
public class SimpleChatResponse extends ChatResponse {

  private final boolean done;
  private final String content;

  public SimpleChatResponse(boolean done, String content) {
    super(List.of());
    this.done = done;
    this.content = content;
  }

  @Override
  @JsonIgnore
  public Generation getResult() {
    return super.getResult();
  }

  @Override
  @JsonIgnore
  public List<Generation> getResults() {
    return super.getResults();
  }

  @Override
  @JsonIgnore
  public ChatResponseMetadata getMetadata() {
    return super.getMetadata();
  }
}
