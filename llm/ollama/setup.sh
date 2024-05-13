#!/bin/sh
echo "Starting Ollama server..."
ollama serve &

echo "Waiting for Ollama server to be active..."
while [ "$(ollama list | grep 'NAME')" == "" ]; do
  sleep 1
done

ollama pull $DEFAULT_TEXT_EMBEDDINGS_MODEL
ollama pull $DEFAULT_TEXT_GENERATION_MODEL
