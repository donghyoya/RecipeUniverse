# stop.sh 수정
echo "Stopping existing Docker containers..."
ls -al
pwd
sudo docker-compose -f /home/ubuntu/recipeUniverse/backend down

