#!/bin/bash

# Create directory if it doesn't exist
mkdir -p /Users/chrishemsley/DentalAI/test-data

# Download sample dental X-ray image from a public dataset
curl -L "https://raw.githubusercontent.com/sunnyclinics/dental-xray-dataset/main/sample-xray.jpg" \
  -o "/Users/chrishemsley/DentalAI/test-data/sample-xray.jpg"

echo "Sample X-ray image downloaded to test-data/sample-xray.jpg" 