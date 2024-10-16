#!/bin/bash

echo "Starting Ollama server..."
ollama serve &

echo "Waiting for Ollama server to be active..."
while [ "$(ollama list | grep 'NAME')" = "" ]; do
  sleep 1
done

# Parse the 'ollama' engine model names from the YAML file
model_names=$(yq e '.llm.models[] | select(.engine == "ollama") | .name' "application.yaml")

# Loop through each line (model name) and pull the model
while IFS= read -r model_name; do
    ollama pull "$model_name"
done <<< "$model_names"
