LOG_FILE="/home/ubuntu/recipeUniverse/backend/deploy_stop.log"

echo $(pwd)

echo "123" >> $LOG_FILE

echo "Stopping existing Docker containers...TEST55" | tee -a $LOG_FILE

ls -al | tee -a $LOG_FILE
pwd | tee -a $LOG_FILE
whoami | tee -a $LOG_FILE

# 필요한 경로로 이동하여 다시 로그에 기록
cd /home/ubuntu/recipeUniverse/backend
ls -al | tee -a $LOG_FILE
pwd | tee -a $LOG_FILE

sudo docker-compose down