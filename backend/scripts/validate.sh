#!/bin/bash
echo "Validating service..."

# 특정 포트가 열려 있는지 확인하여 애플리케이션이 제대로 작동하는지 체크합니다.
if curl -s http://localhost:8080/actuator/health | grep "UP"; then
  echo "Application is running successfully."
  exit 0
else
  echo "Application failed to start."
  exit 1
fi

