#!/bin/bash

echo "Starting Ollama server..."
ollama serve &

echo "Waiting for Ollama server to be active..."
while [ "$(ollama list | grep 'NAME')" = "" ]; do
  sleep 1
done

# Extract all model names
model_names=$(yq e '.llm.default.model | to_entries | map(.value | .[] | .name) | join(" ")' "application.yaml")

read -r -a model_names_array <<< "$model_names"

for model_name in "${model_names_array[@]}"; do
    ollama pull "$model_name"
done
