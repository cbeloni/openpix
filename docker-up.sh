#!/bin/bash
docker build --tag=openpix:latest .
docker run -d -p80:8080  openpix:latest
