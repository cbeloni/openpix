#!/bin/bash
docker build --tag=openpix:latest .
docker run -p80:8080  openpix:latest