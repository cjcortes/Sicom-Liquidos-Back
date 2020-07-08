#!/bin/bash
set -e

# Authenticate with GCloud
gcloud auth activate-service-account --key-file key.json
gcloud config set compute/zone us-central1-a
gcloud config set project hibot-env
gcloud container clusters get-credentials $1

# Set deployment image
fluxctl release --workload "$2:deployment/$3" --update-image "$4:$5" --watch
