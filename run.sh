#!/bin/bash
cd mini-event-api

./gradlew clean build
cd ..

docker-compose build
docker-compose up
