#IMPORTANT - check https://hub.docker.com/r/ollama/ollama for GPU enabling!
FROM ollama/ollama

ARG TEXT_EMBEDDINGS_MODEL
ARG TEXT_GENERATION_MODEL

ENV DEFAULT_TEXT_EMBEDDINGS_MODEL=${TEXT_EMBEDDINGS_MODEL}
ENV DEFAULT_TEXT_GENERATION_MODEL=${TEXT_GENERATION_MODEL}

WORKDIR /usr/local/bin/

COPY setup.sh .
# if we does not need to update llms every time during start need to change startup script
RUN chmod +x setup.sh \
    && ./setup.sh





