#!/bin/bash

gradle clean build

docker-compose build

docker-compose up -d
