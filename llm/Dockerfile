#IMPORTANT - check https://hub.docker.com/r/ollama/ollama for GPU enabling!
FROM ollama/ollama

WORKDIR /usr/local/bin/

COPY setup.sh .
# if we does not need to update llms every time during start need to change startup script
RUN chmod +x setup.sh \
    && ./setup.sh





