# stop.sh 수정
#!/bin/bash

# 로그 파일 경로 설정
LOG_FILE="/home/ubuntu/recipeUniverse/backend/deploy_stop.log"

# 로그 파일 생성 및 기존 내용 삭제
echo "Deployment Log - $(date)" > $LOG_FILE

# 로그에 출력값 기록
echo "Stopping existing Docker containers..." | tee -a $LOG_FILE
ls -al | tee -a $LOG_FILE
pwd | tee -a $LOG_FILE
whoami | tree -a $LOG_FILE

# 필요한 경로로 이동하여 다시 로그에 기록
cd /home/ubuntu/recipeUniverse/backend  # docker-compose.yml이 있는 경로로 변경
ls -al | tee -a $LOG_FILE
pwd | tee -a $LOG_FILE

sudo docker-compose down