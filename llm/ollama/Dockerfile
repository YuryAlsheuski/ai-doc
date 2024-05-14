FROM docker.io/mikefarah/yq:4.30.6 as yq

#IMPORTANT - check https://hub.docker.com/r/ollama/ollama for GPU enabling!
FROM ollama/ollama

WORKDIR /usr/local/bin/

COPY ../ollama/setup.sh .
COPY ../src/main/resources/application.yaml .
COPY --from=yq "/usr/bin/yq" .

# if we does not need to update llms every time during start need to change startup script
RUN chmod +x setup.sh \
    && ./setup.sh





