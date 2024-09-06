# stop.sh
echo "try catching 1"
pwd
ls -al
sudo cd /home/ubuntu/recipeUniverse/backend/ # 정확한 경로로 이동
if [ $? -ne 0 ]; then
    echo "Failed to change directory."
    exit 1
fi
pwd
ls -al
echo "Stopping existing Docker containers..."
sudo docker-compose down
if [ $? -ne 0 ]; then
    echo "Failed to stop Docker containers."
    exit 1
fi
