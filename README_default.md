# 

## Model
www.msaez.io/#/53361445/storming/walkingstudio

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd infra
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- walking history
- weather
- user
- user walking stat
- team walking stat
- company walking stat
- challenge
- calculate point


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- walking history
```
 http :8088/walkingHsts pUserId="p_user_id" teamId="team_id" comId="com_id" baseDate="base_date" baseTime="base_time" walking="walking" 
```
- weather
```
 http :8088/usrtFcstHsts baseDate="base_date" baseTime="base_time" nx="nx" ny="ny" category="category" fcstDate="fcst_date" fcstTime="fcst_time" fcstValue="fcst_value" 
 http :8088/weatherStnInfos id="id" divisionLevel1="division_level1" divisionLevel2="division_level2" nx="nx" ny="ny" 
```
- user
```
 http :8088/users pUserId="p_user_id" pUserName="p_user_name" comId="com_id" teamId="team_id" otp="otp" point="point" 
```
- user walking stat
```
 http :8088/walkingStatByUsers pUserId="p_user_id" baseDate="base_date" walking="walking" ranking="ranking" 
```
- team walking stat
```
 http :8088/walkingStatByTeams teamId="team_id" baseDate="base_date" walking="walking" ranking="ranking" 
```
- company walking stat
```
 http :8088/walkingStatByCompanies comId="com_id" baseDate="base_date" walking="walking" ranking="ranking" 
```
- challenge
```
 http :8088/challengeInfos challengeId="challenge_id" missionStDt="mission_st_dt" missionFnDt="mission_fn_dt" range="range" point="point" 
 http :8088/challengeHsts challengeId="challenge_id" pUserId="p_user_id" rewardDt="reward_dt" rewardYn="reward_yn" 
```
- calculate point
```
 http :8088/pointStandardInfos baseDate="base_date" baseTime="base_time" nx="nx" ny="ny" weight="weight" 
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```
